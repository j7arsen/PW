package com.j7arsen.pw.di.main.module;

import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.model.mapper.IObjectModelMapper;
import com.j7arsen.pw.data.model.net.user.UserInfoEntity;
import com.j7arsen.pw.data.repository.user.UserInfoRepository;
import com.j7arsen.pw.data.repository.user.mapper.UserInfoModelMapper;
import com.j7arsen.pw.di.main.MainScope;
import com.j7arsen.pw.domain.model.UserInfoModel;
import com.j7arsen.pw.domain.repository.IUserInfoRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 28.02.2018.
 */
@Module
public class MainRepositoryModule {

    @Provides
    @MainScope
    public IObjectModelMapper<UserInfoEntity, UserInfoModel> provideMainModelMapper() {
        return new UserInfoModelMapper();
    }

    @Provides
    @MainScope
    public IUserInfoRepository provideUserRepository(ApiService apiService, IObjectModelMapper<UserInfoEntity, UserInfoModel> modelMapper) {
        return new UserInfoRepository(apiService, modelMapper);
    }

}
