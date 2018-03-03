package com.j7arsen.pw.ui.transaction.list;

import com.arellomobile.mvp.InjectViewState;
import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.domain.interactor.GetTransactionListUseCase;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.manager.DataManager;
import com.j7arsen.pw.utils.Screens;
import com.j7arsen.pw.utils.executor.UpdateBalanceEvent;
import com.j7arsen.pw.utils.helper.ErrorHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.result.ResultListener;

/**
 * Created by arsen on 01.03.2018.
 */
@InjectViewState
public class TransactionListPresenter extends BasePresenter<ITransactionListView> {

    @Inject
    @Global
    Router router;

    @Inject
    ErrorHandler errorHandler;

    @Inject
    DataManager dataManager;

    @Inject
    GetTransactionListUseCase getTransactionListUseCase;

    public TransactionListPresenter() {
        ComponentManager.getInstance().getTransactionComponent().inject(this);
        setResultListener();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadTransaction(false);
    }

    public void openDetailScreen(TransTokenModel model) {
        router.navigateTo(Screens.TRANSACTION_DETAIL_SCREEN, model);
    }

    public void loadTransaction(boolean isRefresh) {
        getTransactionListUseCase.execute(dataManager.getTokenModel(), new DisposableSingleObserver<List<TransTokenModel>>() {
            @Override
            protected void onStart() {
                if (!isRefresh) {
                    getViewState().hideEmptyListView();
                    getViewState().showProgress();
                }
            }

            @Override
            public void onSuccess(List<TransTokenModel> transTokenModels) {
                getViewState().showList(transTokenModels);
                if (transTokenModels == null || transTokenModels.isEmpty()) {
                    getViewState().showEmptyListView();
                }
                getViewState().hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(errorHandler.getError(e));
            }
        });
    }

    private void setResultListener() {
        router.setResultListener(Screens.TRANSACTION_RESULT, new ResultListener() {
            @Override
            public void onResult(Object resultData) {
                loadTransaction(false);
                EventBus.getDefault().post(new UpdateBalanceEvent());
            }
        });
    }

    private void removeResultListener() {
        router.removeResultListener(Screens.TRANSACTION_RESULT);
    }

    @Override
    public void onDestroy() {
        removeResultListener();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }
}
