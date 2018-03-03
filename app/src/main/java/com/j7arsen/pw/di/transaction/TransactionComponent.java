package com.j7arsen.pw.di.transaction;

import com.j7arsen.pw.di.transaction.module.TransactionRepositoryModule;
import com.j7arsen.pw.di.transaction.module.TransactionUseCaseModule;
import com.j7arsen.pw.ui.transaction.detail.TransactionDetailFragment;
import com.j7arsen.pw.ui.transaction.detail.TransactionDetailPresenter;
import com.j7arsen.pw.ui.transaction.list.TransactionListFragment;
import com.j7arsen.pw.ui.transaction.list.TransactionListPresenter;
import com.j7arsen.pw.ui.transaction.main.TransactionActivity;
import com.j7arsen.pw.ui.transaction.main.TransactionActivityPresenter;

import dagger.Subcomponent;

/**
 * Created by arsen on 01.03.2018.
 */
@TransactionScope
@Subcomponent(modules = {
        TransactionRepositoryModule.class,
        TransactionUseCaseModule.class})
public interface TransactionComponent {

    void inject(TransactionActivity transactionActivity);

    void inject(TransactionActivityPresenter transactionActivityPresenter);

    void inject(TransactionListFragment transactionListFragment);

    void inject(TransactionListPresenter transactionListPresenter);

    void inject(TransactionDetailFragment transactionDetailFragment);

    void inject(TransactionDetailPresenter transactionDetailPresenter);

    @Subcomponent.Builder
    interface Builder {
        TransactionComponent build();
    }

}
