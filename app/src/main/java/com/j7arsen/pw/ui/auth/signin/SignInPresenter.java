package com.j7arsen.pw.ui.auth.signin;

import com.arellomobile.mvp.InjectViewState;
import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.domain.interactor.SignInUseCase;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.requestbody.SignInObject;
import com.j7arsen.pw.manager.DataManager;
import com.j7arsen.pw.utils.Screens;
import com.j7arsen.pw.utils.helper.ErrorHandler;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.result.ResultListener;

/**
 * Created by arsen on 26.02.2018.
 */
@InjectViewState
public class SignInPresenter extends BasePresenter<ISignInView>{

    @Inject
    @Global
    Router router;

    @Inject
    SignInUseCase signInUseCase;

    @Inject
    ErrorHandler errorHandler;

    @Inject
    DataManager dataManager;

    public SignInPresenter() {
        ComponentManager.getInstance().getAuthComponent().inject(this);
        setResultListener();
    }

    public void openSignUpScreen(){
        router.navigateTo(Screens.SIGN_UP_SCREEN);
    }

    public void signIn(String email, String password){
        signInUseCase.execute(new SignInObject(email, password), new DisposableSingleObserver<TokenModel>() {
            @Override
            protected void onStart() {
                getViewState().showProgress();
            }

            @Override
            public void onSuccess(TokenModel tokenModel) {
                dataManager.setTokenModel(tokenModel);
                getViewState().hideProgress();
                getViewState().successSignIn();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(errorHandler.getError(e));
            }
        });
    }

    public void errorCancel(){
        getViewState().hideError();
    }

    private void setResultListener() {
        router.setResultListener(Screens.SIGN_UP_RESULT, new ResultListener() {
            @Override
            public void onResult(Object resultData) {
                getViewState().successSignIn();
            }
        });
    }

    private void removeResultListener() {
        router.removeResultListener(Screens.SIGN_UP_RESULT);
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
