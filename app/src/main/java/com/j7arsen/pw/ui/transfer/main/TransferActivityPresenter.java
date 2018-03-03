package com.j7arsen.pw.ui.transfer.main;

import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.utils.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 01.03.2018.
 */

public class TransferActivityPresenter extends BasePresenter<ITransferActivityView> {

    @Inject
    @Global
    Router router;

    private TransTokenModel transTokenModel;

    public TransferActivityPresenter(TransTokenModel transTokenModel) {
        ComponentManager.getInstance().getTransferComponent().inject(this);
        this.transTokenModel = transTokenModel;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        openMainScreen();
    }

    public void openMainScreen() {
        router.newRootScreen(Screens.TRANSFER_SCREEN, transTokenModel);
    }

    @Override
    public void onDestroy() {
        ComponentManager.getInstance().destroyTransferComponent();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }

}
