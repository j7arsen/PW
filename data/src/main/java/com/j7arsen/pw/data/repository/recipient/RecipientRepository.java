package com.j7arsen.pw.data.repository.recipient;

import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.api.RequestField;
import com.j7arsen.pw.data.model.mapper.IObjectListModelMapper;
import com.j7arsen.pw.data.model.net.transfer.RecipientEntity;
import com.j7arsen.pw.domain.model.RecipientModel;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.requestbody.RecipientFilter;
import com.j7arsen.pw.domain.repository.IRecipientRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by arsen on 01.03.2018.
 */

public class RecipientRepository implements IRecipientRepository {

    private ApiService apiService;
    private IObjectListModelMapper<RecipientEntity, RecipientModel> mapper;

    public RecipientRepository(ApiService apiService, IObjectListModelMapper<RecipientEntity, RecipientModel> mapper) {
        this.apiService = apiService;
        this.mapper = mapper;
    }

    @Override
    public Single<List<RecipientModel>> getRecipient(TokenModel tokenModel, RecipientFilter recipientFilter) {
        return apiService.getUserList(RequestField.getHeader(tokenModel), recipientFilter).map(userList -> mapper.mapEntityToDomainList(userList));
    }
}
