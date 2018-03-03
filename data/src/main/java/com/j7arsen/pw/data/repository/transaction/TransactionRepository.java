package com.j7arsen.pw.data.repository.transaction;

import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.api.RequestField;
import com.j7arsen.pw.data.model.mapper.IObjectListModelMapper;
import com.j7arsen.pw.data.model.net.transfer.TransTokenEntity;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.domain.repository.ITransactionRepository;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransactionRepository implements ITransactionRepository {

    private ApiService apiService;
    private IObjectListModelMapper<TransTokenEntity, TransTokenModel> mapper;

    public TransactionRepository(ApiService apiService, IObjectListModelMapper<TransTokenEntity, TransTokenModel> mapper) {
        this.apiService = apiService;
        this.mapper = mapper;
    }

    @Override
    public Single<List<TransTokenModel>> getTransactionList(TokenModel tokenModel) {
        return apiService.getTransactionList(RequestField.getHeader(tokenModel)).map(transactionObjectList -> mapper.mapEntityToDomainList(transactionObjectList.getTransTokenEntityList())).map(transactionModelList -> {
            Collections.reverse(transactionModelList);
            return transactionModelList;
        });
    }
}
