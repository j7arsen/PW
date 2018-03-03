package com.j7arsen.pw.domain.interactor;

import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.base.single.BaseSingleUseCaseWithParams;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.domain.model.requestbody.TransactionObject;
import com.j7arsen.pw.domain.repository.ITransferRepository;

import io.reactivex.Single;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransferUseCase extends BaseSingleUseCaseWithParams<TransTokenModel, TransferUseCase.Params> {

    private ITransferRepository transferRepository;

    public TransferUseCase(ITransferRepository transferRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.transferRepository = transferRepository;
    }

    @Override
    protected Single<TransTokenModel> buildUseCase(Params params) {
        return transferRepository.pay(params.getTokenModel(), params.getTransactionObject());
    }

    public static class Params{

        private TokenModel tokenModel;
        private TransactionObject transactionObject;

        public Params(TokenModel tokenModel, TransactionObject transactionObject) {
            this.tokenModel = tokenModel;
            this.transactionObject = transactionObject;
        }

        public TokenModel getTokenModel() {
            return tokenModel;
        }

        public void setTokenModel(TokenModel tokenModel) {
            this.tokenModel = tokenModel;
        }

        public TransactionObject getTransactionObject() {
            return transactionObject;
        }

        public void setTransactionObject(TransactionObject transactionObject) {
            this.transactionObject = transactionObject;
        }
    }
}
