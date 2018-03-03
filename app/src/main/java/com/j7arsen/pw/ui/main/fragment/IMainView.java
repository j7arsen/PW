package com.j7arsen.pw.ui.main.fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.j7arsen.pw.domain.model.UserInfoModel;

/**
 * Created by arsen on 28.02.2018.
 */

public interface IMainView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError(String error);

    @StateStrategyType(SingleStateStrategy.class)
    void showUserIfo(UserInfoModel userInfoModel);


}
