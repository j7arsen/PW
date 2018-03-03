package com.j7arsen.pw.di.main.module;

import com.j7arsen.pw.di.main.MainScope;
import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.UserInfoUseCase;
import com.j7arsen.pw.domain.repository.IUserInfoRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 28.02.2018.
 */
@Module
public class MainUseCaseModule {

    @Provides
    @MainScope
    public UserInfoUseCase provideUserInfoUseCase(IUserInfoRepository userInfoRepository, IThreadExecutor threadExecutor) {
        return new UserInfoUseCase(userInfoRepository, threadExecutor);
    }

}
