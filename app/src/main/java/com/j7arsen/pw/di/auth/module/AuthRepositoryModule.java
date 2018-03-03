package com.j7arsen.pw.di.auth.module;

import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.model.mapper.IObjectModelMapper;
import com.j7arsen.pw.data.model.net.auth.TokenEntity;
import com.j7arsen.pw.data.repository.auth.AuthRepository;
import com.j7arsen.pw.data.repository.auth.mapper.AuthModelMapper;
import com.j7arsen.pw.di.auth.AuthScope;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.repository.IAuthRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 26.02.2018.
 */
@Module
public class AuthRepositoryModule {

    @Provides
    @AuthScope
    public IObjectModelMapper<TokenEntity, TokenModel> provideAuthModelMapper() {
        return new AuthModelMapper();
    }

    @Provides
    @AuthScope
    public IAuthRepository provideAuthRepository(ApiService apiService, IObjectModelMapper<TokenEntity, TokenModel> modelMapper) {
        return new AuthRepository(apiService, modelMapper);
    }

}
