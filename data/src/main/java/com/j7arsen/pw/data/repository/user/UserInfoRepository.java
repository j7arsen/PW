package com.j7arsen.pw.data.repository.user;

import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.api.RequestField;
import com.j7arsen.pw.data.model.mapper.IObjectModelMapper;
import com.j7arsen.pw.data.model.net.user.UserInfoEntity;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.UserInfoModel;
import com.j7arsen.pw.domain.repository.IUserInfoRepository;

import io.reactivex.Single;

/**
 * Created by arsen on 28.02.2018.
 */

public class UserInfoRepository implements IUserInfoRepository {

    private ApiService apiService;
    private IObjectModelMapper<UserInfoEntity, UserInfoModel> mapper;

    public UserInfoRepository(ApiService apiService, IObjectModelMapper<UserInfoEntity, UserInfoModel> mapper) {
        this.apiService = apiService;
        this.mapper = mapper;
    }

    @Override
    public Single<UserInfoModel> getUserInfo(TokenModel tokenModel) {
        return apiService.getUserInfo(RequestField.getHeader(tokenModel)).map(userInfo -> mapper.mapEntityToDomain(userInfo.getUserInfoEntity()));
    }
}
