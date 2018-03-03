package com.j7arsen.pw.di.transaction.module;

import com.j7arsen.pw.di.transaction.TransactionScope;
import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.GetTransactionListUseCase;
import com.j7arsen.pw.domain.repository.ITransactionRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 01.03.2018.
 */
@Module
public class TransactionUseCaseModule {

    @Provides
    @TransactionScope
    public GetTransactionListUseCase provideGetTransactionUseCase(ITransactionRepository transactionRepository, IThreadExecutor threadExecutor) {
        return new GetTransactionListUseCase(transactionRepository, threadExecutor);
    }

}
