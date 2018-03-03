package com.j7arsen.pw.di.transaction.module;

import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.model.mapper.IObjectListModelMapper;
import com.j7arsen.pw.data.model.net.transfer.TransTokenEntity;
import com.j7arsen.pw.data.repository.transaction.TransactionRepository;
import com.j7arsen.pw.data.repository.transaction.mapper.TransactionMapper;
import com.j7arsen.pw.di.transaction.TransactionScope;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.domain.repository.ITransactionRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 01.03.2018.
 */
@Module
public class TransactionRepositoryModule {

    @Provides
    @TransactionScope
    public IObjectListModelMapper<TransTokenEntity, TransTokenModel> provideTransModelMapper() {
        return new TransactionMapper();
    }

    @Provides
    @TransactionScope
    public ITransactionRepository provideTransactionRepository(ApiService apiService, IObjectListModelMapper<TransTokenEntity, TransTokenModel> modelMapper) {
        return new TransactionRepository(apiService, modelMapper);
    }

}
