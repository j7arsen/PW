package com.j7arsen.pw.domain.interactor;

import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.base.single.BaseSingleUseCaseWithParams;
import com.j7arsen.pw.domain.model.requestbody.SignUpObject;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.repository.IAuthRepository;

import io.reactivex.Single;

/**
 * Created by arsen on 26.02.2018.
 */

public class SignUpUseCase extends BaseSingleUseCaseWithParams<TokenModel, SignUpObject>{

    private IAuthRepository authorizationRepository;

    public SignUpUseCase(IAuthRepository authorizationRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    protected Single<TokenModel> buildUseCase(SignUpObject signUpObject) {
        return authorizationRepository.signUp(signUpObject);
    }
}
