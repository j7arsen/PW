package com.j7arsen.pw.data.model.net.transfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransTokenObjectEntity {

    @SerializedName("trans_token")
    @Expose
    private TransTokenEntity transTokenEntity;

    public TransTokenEntity getTransTokenEntity() {
        return transTokenEntity;
    }

    public void setTransTokenEntity(TransTokenEntity transTokenEntity) {
        this.transTokenEntity = transTokenEntity;
    }
}
