package com.j7arsen.pw.ui.transaction.list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.j7arsen.pw.domain.model.TransTokenModel;

import java.util.List;

/**
 * Created by arsen on 01.03.2018.
 */

public interface ITransactionListView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError(String error);

    @StateStrategyType(SingleStateStrategy.class)
    void showList(List<TransTokenModel> photos);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showEmptyListView();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideEmptyListView();

}
