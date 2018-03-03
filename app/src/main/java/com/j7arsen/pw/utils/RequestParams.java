package com.j7arsen.pw.utils;

import java.util.HashMap;

/**
 * Created by arsen on 26.02.2018.
 */

public class RequestParams {

    private HashMap<String, String> params;

    public RequestParams() {
        params = new HashMap<>();
    }

    public static Builder newBuilder() {
        return new RequestParams().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder append(String key, String value) {
            params.put(key,value);
            return this;
        }


        public HashMap<String,String> build(){
            return params;
        }
    }

}
