package com.j7arsen.pw.data.repository.auth;

import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.model.mapper.IObjectModelMapper;
import com.j7arsen.pw.data.model.net.auth.TokenEntity;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.requestbody.SignInObject;
import com.j7arsen.pw.domain.model.requestbody.SignUpObject;
import com.j7arsen.pw.domain.repository.IAuthRepository;

import io.reactivex.Single;
/**
 * Created by arsen on 26.02.2018.
 */

public class AuthRepository implements IAuthRepository {

    private ApiService apiService;
    private IObjectModelMapper<TokenEntity, TokenModel> mapper;

    public AuthRepository(ApiService apiService, IObjectModelMapper<TokenEntity, TokenModel> mapper) {
        this.apiService = apiService;
        this.mapper = mapper;
    }

    @Override
    public Single<TokenModel> signUp(SignUpObject signUpObjectEntity) {
        return apiService.signUp(signUpObjectEntity).map(token -> mapper.mapEntityToDomain(token));
    }

    @Override
    public Single<TokenModel> signIn(SignInObject signInObjectEntity) {
        return apiService.signIn(signInObjectEntity).map(token -> mapper.mapEntityToDomain(token));
    }
}
