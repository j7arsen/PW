package com.j7arsen.pw.data.repository.transfer;

import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.api.RequestField;
import com.j7arsen.pw.data.model.mapper.IObjectModelMapper;
import com.j7arsen.pw.data.model.net.transfer.TransTokenEntity;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.domain.model.requestbody.TransactionObject;
import com.j7arsen.pw.domain.repository.ITransferRepository;

import io.reactivex.Single;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransferRepository implements ITransferRepository {

    private ApiService apiService;
    private IObjectModelMapper<TransTokenEntity, TransTokenModel> mapper;

    public TransferRepository(ApiService apiService, IObjectModelMapper<TransTokenEntity, TransTokenModel> mapper) {
        this.apiService = apiService;
        this.mapper = mapper;
    }

    @Override
    public Single<TransTokenModel> pay(TokenModel tokenModel, TransactionObject transactionObject) {
        return apiService.createTransaction(RequestField.getHeader(tokenModel), transactionObject).map(transToken -> mapper.mapEntityToDomain(transToken.getTransTokenEntity()));
    }

}
