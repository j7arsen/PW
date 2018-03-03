package com.j7arsen.pw.ui.transaction.detail;

import com.arellomobile.mvp.InjectViewState;
import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.manager.DataManager;
import com.j7arsen.pw.utils.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 01.03.2018.
 */
@InjectViewState
public class TransactionDetailPresenter extends BasePresenter<ITransactionDetailView> {

    @Inject
    @Global
    Router router;

    @Inject
    DataManager dataManager;

    private TransTokenModel transTokenModel;

    public TransactionDetailPresenter(TransTokenModel transTokenModel) {
        ComponentManager.getInstance().getTransactionComponent().inject(this);
        this.transTokenModel = transTokenModel;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().fillTransDetailData(dataManager.getUserInfoModel().getName(), transTokenModel);
    }

    public void transferSuccess() {
        router.exitWithResult(Screens.TRANSACTION_RESULT, null);
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }
}
