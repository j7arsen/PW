package com.j7arsen.pw.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by arsen on 26.02.2018.
 */

public class PrimitiveConverterFactory extends Converter.Factory {

    public static PrimitiveConverterFactory create() {
        return new PrimitiveConverterFactory();
    }

    private PrimitiveConverterFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {
                    return value.string();
                }
            };
        } else if(type == Integer.class) {
            return new Converter<ResponseBody, Integer>() {
                @Override
                public Integer convert(ResponseBody value) throws IOException {
                    return Integer.valueOf(value.string());
                }
            };
        } else if(type == Double.class) {
            return new Converter<ResponseBody, Double>() {
                @Override
                public Double convert(ResponseBody value) throws IOException {
                    return Double.valueOf(value.string());
                }
            };
        } else if(type == Boolean.class) {
            return new Converter<ResponseBody, Boolean>() {
                @Override
                public Boolean convert(ResponseBody value) throws IOException {
                    return Boolean.valueOf(value.string());
                }
            };
        }
        return null;
    }
}
