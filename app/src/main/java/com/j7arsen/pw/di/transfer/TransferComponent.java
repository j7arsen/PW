package com.j7arsen.pw.di.transfer;

import com.j7arsen.pw.di.transfer.module.TransferRepositoryModule;
import com.j7arsen.pw.di.transfer.module.TransferUseCaseModule;
import com.j7arsen.pw.ui.transfer.main.TransferActivity;
import com.j7arsen.pw.ui.transfer.main.TransferActivityPresenter;
import com.j7arsen.pw.ui.transfer.transfer.TransferFragment;
import com.j7arsen.pw.ui.transfer.transfer.TransferPresenter;
import com.j7arsen.pw.ui.transfer.success.TransferSuccessFragment;

import dagger.Subcomponent;

/**
 * Created by arsen on 01.03.2018.
 */
@TransferScope
@Subcomponent(modules = {
        TransferRepositoryModule.class,
        TransferUseCaseModule.class})
public interface TransferComponent {

    void inject(TransferActivity transferActivity);

    void inject(TransferActivityPresenter transferActivityPresenter);

    void inject(TransferFragment transferFragment);

    void inject(TransferPresenter transferPresenter);

    void inject(TransferSuccessFragment transferSuccessFragment);
    
    @Subcomponent.Builder
    interface Builder {
        TransferComponent build();
    }

}
