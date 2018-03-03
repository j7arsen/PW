package com.j7arsen.pw.ui.splash;

import com.arellomobile.mvp.InjectViewState;
import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.manager.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by arsen on 28.02.2018.
 */
@InjectViewState
public class SplashPresenter extends BasePresenter<ISplashView>{

    @Inject
    DataManager dataManager;

    private Disposable mDisposable;

    public SplashPresenter(){
        ComponentManager.getInstance().getAppComponent().inject(this);
        mDisposable = Disposables.empty();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        startSplashTimer();
    }

    private void startSplashTimer(){
        mDisposable = dataManager.getTimerSplash().subscribe(aLong -> getViewState().setAuthorized(dataManager.getTokenModel() != null));
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onDestroy() {
        if(!mDisposable.isDisposed()){
            mDisposable.isDisposed();
        }
        super.onDestroy();
    }
}
