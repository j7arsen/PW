package com.j7arsen.pw.domain.model;

import java.io.Serializable;

/**
 * Created by arsen on 26.02.2018.
 */

public class TokenModel implements Serializable{

    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
