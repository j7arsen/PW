package com.j7arsen.pw.domain.interactor;

import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.base.single.BaseSingleUseCaseWithParams;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.domain.repository.ITransactionRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by arsen on 02.03.2018.
 */

public class GetTransactionListUseCase extends BaseSingleUseCaseWithParams<List<TransTokenModel>, TokenModel> {

    private ITransactionRepository transactionRepository;

    public GetTransactionListUseCase(ITransactionRepository transactionRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.transactionRepository = transactionRepository;
    }

    @Override
    protected Single<List<TransTokenModel>> buildUseCase(TokenModel tokenModel) {
        return transactionRepository.getTransactionList(tokenModel);
    }

}
