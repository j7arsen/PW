package com.j7arsen.pw.data.model.net.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arsen on 26.02.2018.
 */

public class TokenEntity {

    @SerializedName("id_token")
    @Expose
    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

}
