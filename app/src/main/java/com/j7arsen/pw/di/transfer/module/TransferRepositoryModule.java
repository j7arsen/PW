package com.j7arsen.pw.di.transfer.module;

import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.model.mapper.IObjectListModelMapper;
import com.j7arsen.pw.data.model.mapper.IObjectModelMapper;
import com.j7arsen.pw.data.model.net.transfer.RecipientEntity;
import com.j7arsen.pw.data.model.net.transfer.TransTokenEntity;
import com.j7arsen.pw.data.repository.transfer.TransferRepository;
import com.j7arsen.pw.data.repository.transfer.mapper.TransferMapper;
import com.j7arsen.pw.data.repository.recipient.RecipientRepository;
import com.j7arsen.pw.data.repository.recipient.mapper.RecipientMapper;
import com.j7arsen.pw.di.transfer.TransferScope;
import com.j7arsen.pw.domain.model.RecipientModel;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.domain.repository.ITransferRepository;
import com.j7arsen.pw.domain.repository.IRecipientRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 01.03.2018.
 */
@Module
public class TransferRepositoryModule {

    @Provides
    @TransferScope
    public IObjectListModelMapper<RecipientEntity, RecipientModel> provideRecipientMapper() {
        return new RecipientMapper();
    }

    @Provides
    @TransferScope
    public IRecipientRepository provideRecipientRepository(ApiService apiService, IObjectListModelMapper<RecipientEntity, RecipientModel> modelMapper) {
        return new RecipientRepository(apiService, modelMapper);
    }

    @Provides
    @TransferScope
    public IObjectModelMapper<TransTokenEntity, TransTokenModel> provideTransferMapper() {
        return new TransferMapper();
    }

    @Provides
    @TransferScope
    public ITransferRepository provideTransferRepository(ApiService apiService, IObjectModelMapper<TransTokenEntity, TransTokenModel> modelMapper) {
        return new TransferRepository(apiService, modelMapper);
    }

}
