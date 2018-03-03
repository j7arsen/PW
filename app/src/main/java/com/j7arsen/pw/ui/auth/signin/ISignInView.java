package com.j7arsen.pw.ui.auth.signin;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by arsen on 26.02.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface ISignInView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void hideError();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void successSignIn();

}
