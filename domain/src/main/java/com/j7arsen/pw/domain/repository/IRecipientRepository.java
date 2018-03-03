package com.j7arsen.pw.domain.repository;

import com.j7arsen.pw.domain.model.RecipientModel;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.requestbody.RecipientFilter;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by arsen on 01.03.2018.
 */

public interface IRecipientRepository {

    Single<List<RecipientModel>> getRecipient(TokenModel tokenModel, RecipientFilter recipientFilter);

}
