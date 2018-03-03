package com.j7arsen.pw.di.app.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j7arsen.pw.app.Constants;
import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.api.Environment;
import com.j7arsen.pw.retrofit.NullOnEmptyConverterFactory;
import com.j7arsen.pw.retrofit.PrimitiveConverterFactory;
import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.helper.ErrorHandler;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arsen on 26.02.2018.
 */
@Module
public class NetModule {

    @Singleton
    @Provides
    public ApiService provideApiService() {
        final Retrofit retrofit = initRetrofit();
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    public ErrorHandler provideErrorHandler(ResUtils utils) {
        return new ErrorHandler(utils);
    }

    private Retrofit initRetrofit() {
        final Retrofit.Builder retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Environment.BASE_URL)
                        .addConverterFactory(initConverterFactory())
                        .addConverterFactory(initNullConverterFactory())
                        .addConverterFactory(initPrimitiveTypeConverterFactory())
                        .addCallAdapterFactory(initCallAdapterFactory())
                        .client(initOkHttpClient());
        return retrofitBuilder.build();
    }

    private Converter.Factory initConverterFactory() {
        return GsonConverterFactory.create(initGson());
    }

    private Converter.Factory initNullConverterFactory() {
        return NullOnEmptyConverterFactory.create();
    }

    private Converter.Factory initPrimitiveTypeConverterFactory() {
        return PrimitiveConverterFactory.create();
    }

    private CallAdapter.Factory initCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    private Gson initGson() {
        return new GsonBuilder()
                .setLenient()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .setVersion(1.0)
                .create();
    }

    private OkHttpClient initOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .addHeader("Accept", "application/json")
                        .build();
                Response response = chain.proceed(request);
                return response;
            }
        });
        builder.addNetworkInterceptor(logging);
        return builder.build();
    }

}
