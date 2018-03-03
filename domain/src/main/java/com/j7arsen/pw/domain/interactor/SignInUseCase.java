package com.j7arsen.pw.domain.interactor;

import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.base.single.BaseSingleUseCaseWithParams;
import com.j7arsen.pw.domain.model.requestbody.SignInObject;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.repository.IAuthRepository;

import io.reactivex.Single;

/**
 * Created by arsen on 26.02.2018.
 */

public class SignInUseCase extends BaseSingleUseCaseWithParams<TokenModel, SignInObject> {

    private IAuthRepository authorizationRepository;

    public SignInUseCase(IAuthRepository authorizationRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    protected Single<TokenModel> buildUseCase(SignInObject signInObject) {
        return authorizationRepository.signIn(signInObject);
    }
}
