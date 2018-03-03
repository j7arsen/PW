package com.j7arsen.pw.retrofit;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by arsen on 26.02.2018.
 */

public class NullOnEmptyConverterFactory extends Converter.Factory {

    public static NullOnEmptyConverterFactory create() {
        return new NullOnEmptyConverterFactory();
    }

    private NullOnEmptyConverterFactory() {
    }
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, java.lang.annotation.Annotation[] annotations, Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return new Converter<ResponseBody, Object>() {
            @Override
            public Object convert(ResponseBody body) throws IOException {
                if (body.contentLength() == 0) return null;
                return delegate.convert(body);
            }
        };
    }
}
