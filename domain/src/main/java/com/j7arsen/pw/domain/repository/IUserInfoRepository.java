package com.j7arsen.pw.domain.repository;

import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.UserInfoModel;

import io.reactivex.Single;

/**
 * Created by arsen on 28.02.2018.
 */

public interface IUserInfoRepository {

    Single<UserInfoModel> getUserInfo(TokenModel tokenModel);

}
