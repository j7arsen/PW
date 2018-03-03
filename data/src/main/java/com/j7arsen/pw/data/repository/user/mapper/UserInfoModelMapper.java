package com.j7arsen.pw.data.repository.user.mapper;

import com.j7arsen.pw.data.model.mapper.IObjectModelMapper;
import com.j7arsen.pw.data.model.net.user.UserInfoEntity;
import com.j7arsen.pw.domain.model.UserInfoModel;

/**
 * Created by arsen on 28.02.2018.
 */

public class UserInfoModelMapper implements IObjectModelMapper<UserInfoEntity, UserInfoModel> {

    @Override
    public UserInfoModel mapEntityToDomain(UserInfoEntity userInfoEntity) {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setId(userInfoEntity.getId());
        userInfoModel.setName(userInfoEntity.getName());
        userInfoModel.setEmail(userInfoEntity.getEmail());
        userInfoModel.setBalance(userInfoEntity.getBalance());
        return userInfoModel;
    }

    @Override
    public UserInfoEntity mapDomainToEntity(UserInfoModel userInfoModel) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(userInfoModel.getId());
        userInfoEntity.setName(userInfoModel.getName());
        userInfoEntity.setEmail(userInfoModel.getEmail());
        userInfoEntity.setBalance(userInfoModel.getBalance());
        return userInfoEntity;
    }
}
