package com.j7arsen.pw.data.api;

import com.j7arsen.pw.data.model.net.auth.TokenEntity;
import com.j7arsen.pw.data.model.net.transfer.RecipientEntity;
import com.j7arsen.pw.data.model.net.transfer.TransTokenListObjectEntity;
import com.j7arsen.pw.data.model.net.transfer.TransTokenObjectEntity;
import com.j7arsen.pw.data.model.net.user.UserInfoObjectEntity;
import com.j7arsen.pw.domain.model.requestbody.RecipientFilter;
import com.j7arsen.pw.domain.model.requestbody.SignInObject;
import com.j7arsen.pw.domain.model.requestbody.SignUpObject;
import com.j7arsen.pw.domain.model.requestbody.TransactionObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by arsen on 26.02.2018.
 */

public interface ApiService {

    @POST(Environment.SIGN_UP)
    Single<TokenEntity> signUp(@Body SignUpObject signUpObject);

    @POST(Environment.SIGN_IN)
    Single<TokenEntity> signIn(@Body SignInObject signInObject);

    @GET(Environment.GET_USER_INFO)
    Single<UserInfoObjectEntity> getUserInfo(@Header(RequestField.AUTHORIZATION) String tokenId);

    @POST(Environment.GET_USER_LIST)
    Single<List<RecipientEntity>> getUserList(@Header(RequestField.AUTHORIZATION) String tokenId, @Body RecipientFilter recipientFilter);

    @POST(Environment.GET_USER_LIST)
    Observable<List<RecipientEntity>> searchUserList(@Header(RequestField.AUTHORIZATION) String tokenId, @Body RecipientFilter recipientFilter);

    @POST(Environment.TRANSACTION)
    Single<TransTokenObjectEntity> createTransaction(@Header(RequestField.AUTHORIZATION) String tokenId, @Body TransactionObject transactionObject);

    @GET(Environment.GET_TRANSACTION_LIST)
    Single<TransTokenListObjectEntity> getTransactionList(@Header(RequestField.AUTHORIZATION) String tokenId);
}


