package com.j7arsen.pw.data.api;

import com.j7arsen.pw.domain.model.TokenModel;

/**
 * Created by arsen on 28.02.2018.
 */

public class RequestField {

    public static final String AUTHORIZATION = "Authorization";

    public static String getHeader(TokenModel tokenModel){
        return "Bearer "  + tokenModel.getTokenId();
    }

}
