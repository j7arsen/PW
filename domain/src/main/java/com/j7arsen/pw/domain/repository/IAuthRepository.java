package com.j7arsen.pw.domain.repository;

import com.j7arsen.pw.domain.model.requestbody.SignInObject;
import com.j7arsen.pw.domain.model.requestbody.SignUpObject;
import com.j7arsen.pw.domain.model.TokenModel;

import io.reactivex.Single;

/**
 * Created by arsen on 26.02.2018.
 */

public interface IAuthRepository {

    Single<TokenModel> signUp(SignUpObject signUpObjectEntity);

    Single<TokenModel> signIn(SignInObject signInObjectEntity);

}
