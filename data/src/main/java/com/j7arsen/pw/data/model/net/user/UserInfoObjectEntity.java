package com.j7arsen.pw.data.model.net.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arsen on 28.02.2018.
 */

public class UserInfoObjectEntity {

    @SerializedName("user_info_token")
    @Expose
    private UserInfoEntity userInfoEntity;

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }
}
