package com.j7arsen.pw.ui.main.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.domain.interactor.UserInfoUseCase;
import com.j7arsen.pw.domain.model.UserInfoModel;
import com.j7arsen.pw.manager.DataManager;
import com.j7arsen.pw.utils.helper.ErrorHandler;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 28.02.2018.
 */
@InjectViewState
public class MainPresenter extends BasePresenter<IMainView> {

    @Inject
    @Global
    Router router;

    @Inject
    UserInfoUseCase userInfoUseCase;

    @Inject
    ErrorHandler errorHandler;

    @Inject
    DataManager dataManager;

    public MainPresenter() {
        ComponentManager.getInstance().getMainComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadUserInfoData(false);
    }

    public void loadUserInfoData(boolean isRefresh) {
        userInfoUseCase.execute(dataManager.getTokenModel(), new DisposableSingleObserver<UserInfoModel>() {
            @Override
            protected void onStart() {
                if(!isRefresh) {
                    getViewState().showProgress();
                }
            }

            @Override
            public void onSuccess(UserInfoModel userInfoModel) {
                dataManager.setUserInfoModel(userInfoModel);
                getViewState().showUserIfo(userInfoModel);
                getViewState().hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(errorHandler.getError(e));
            }
        });
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }
}
