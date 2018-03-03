package com.j7arsen.pw.domain.repository;

import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.TransTokenModel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by arsen on 02.03.2018.
 */

public interface ITransactionRepository {

    Single<List<TransTokenModel>> getTransactionList(TokenModel tokenModel);

}
