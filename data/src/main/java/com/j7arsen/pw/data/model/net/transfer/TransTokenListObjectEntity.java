package com.j7arsen.pw.data.model.net.transfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransTokenListObjectEntity {

    @SerializedName("trans_token")
    @Expose
    private List<TransTokenEntity> transTokenEntityList;

    public List<TransTokenEntity> getTransTokenEntityList() {
        return transTokenEntityList;
    }

    public void setTransTokenEntityList(List<TransTokenEntity> transTokenEntityList) {
        this.transTokenEntityList = transTokenEntityList;
    }
}
