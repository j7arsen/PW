package com.j7arsen.pw.ui.auth.signup;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by arsen on 26.02.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface ISignUpView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void hideError();

    @StateStrategyType(SkipStrategy.class)
    void successSignUp();

}
