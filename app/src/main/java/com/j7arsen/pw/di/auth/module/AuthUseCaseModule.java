package com.j7arsen.pw.di.auth.module;

import com.j7arsen.pw.di.auth.AuthScope;
import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.SignInUseCase;
import com.j7arsen.pw.domain.interactor.SignUpUseCase;
import com.j7arsen.pw.domain.repository.IAuthRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 26.02.2018.
 */
@Module
public class AuthUseCaseModule {

    @Provides
    @AuthScope
    public SignUpUseCase provideSignUpUseCase(IAuthRepository authorizationRepository, IThreadExecutor threadExecutor) {
        return new SignUpUseCase(authorizationRepository, threadExecutor);
    }

    @Provides
    @AuthScope
    public SignInUseCase provideSignInUseCase(IAuthRepository authorizationRepository, IThreadExecutor threadExecutor) {
        return new SignInUseCase(authorizationRepository, threadExecutor);
    }

}
