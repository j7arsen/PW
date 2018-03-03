package com.j7arsen.pw.data.repository.auth.mapper;

import com.j7arsen.pw.data.model.mapper.IObjectModelMapper;
import com.j7arsen.pw.data.model.net.auth.TokenEntity;
import com.j7arsen.pw.domain.model.TokenModel;

/**
 * Created by arsen on 26.02.2018.
 */

public class AuthModelMapper implements IObjectModelMapper<TokenEntity, TokenModel> {

    @Override
    public TokenModel mapEntityToDomain(TokenEntity tokenEntity) {
        TokenModel tokenModel = new TokenModel();
        tokenModel.setTokenId(tokenEntity.getTokenId());
        return tokenModel;
    }

    @Override
    public TokenEntity mapDomainToEntity(TokenModel tokenModel) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setTokenId(tokenModel.getTokenId());
        return tokenEntity;
    }

}
