package com.j7arsen.pw.domain.repository;

import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.domain.model.requestbody.TransactionObject;

import io.reactivex.Single;

/**
 * Created by arsen on 02.03.2018.
 */

public interface ITransferRepository {

    Single<TransTokenModel> pay(TokenModel tokenModel, TransactionObject transactionObject);

}
