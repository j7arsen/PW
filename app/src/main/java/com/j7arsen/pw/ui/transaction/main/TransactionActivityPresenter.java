package com.j7arsen.pw.ui.transaction.main;

import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.utils.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 01.03.2018.
 */

public class TransactionActivityPresenter extends BasePresenter<ITransactionActivityView> {

    @Inject
    @Global
    Router router;

    public TransactionActivityPresenter() {
        ComponentManager.getInstance().getTransactionComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        openMainScreen();
    }

    public void openMainScreen() {
        router.newRootScreen(Screens.TRANSACTION_LIST_SCREEN);
    }

    @Override
    public void onDestroy() {
        ComponentManager.getInstance().destroyTransactionComponent();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }
}
