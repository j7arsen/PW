package com.j7arsen.pw.ui.auth.signup;

import com.arellomobile.mvp.InjectViewState;
import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.domain.interactor.SignUpUseCase;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.requestbody.SignUpObject;
import com.j7arsen.pw.manager.DataManager;
import com.j7arsen.pw.utils.helper.ErrorHandler;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 26.02.2018.
 */
@InjectViewState
public class SignUpPresenter extends BasePresenter<ISignUpView>{

    @Inject
    SignUpUseCase signUpUseCase;

    @Inject
    ErrorHandler errorHandler;

    @Inject
    DataManager dataManager;

    private Router router;

    public SignUpPresenter(Router router) {
        this.router = router;
        ComponentManager.getInstance().getAuthComponent().inject(this);
    }

    public void signUp(String name, String email, String password){
        signUpUseCase.execute(new SignUpObject(name, password, email), new DisposableSingleObserver<TokenModel>() {
            @Override
            protected void onStart() {
                getViewState().showProgress();
            }

            @Override
            public void onSuccess(TokenModel tokenModel) {
                dataManager.setTokenModel(tokenModel);
                getViewState().hideProgress();
                getViewState().successSignUp();
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

    @Override
    public void onBackPressed() {
        router.exit();
    }

}
