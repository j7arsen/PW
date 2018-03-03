package com.j7arsen.pw.di.transfer.module;

import com.j7arsen.pw.di.transfer.TransferScope;
import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.GetRecipientUseCase;
import com.j7arsen.pw.domain.interactor.TransferUseCase;
import com.j7arsen.pw.domain.repository.ITransferRepository;
import com.j7arsen.pw.domain.repository.IRecipientRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 01.03.2018.
 */
@Module
public class TransferUseCaseModule {

    @Provides
    @TransferScope
    public GetRecipientUseCase provideGetRecipientUseCase(IRecipientRepository recipientRepository, IThreadExecutor threadExecutor) {
        return new GetRecipientUseCase(recipientRepository, threadExecutor);
    }

    @Provides
    @TransferScope
    public TransferUseCase provideTransferUseCase(ITransferRepository transferRepository, IThreadExecutor threadExecutor) {
        return new TransferUseCase(transferRepository, threadExecutor);
    }

}
