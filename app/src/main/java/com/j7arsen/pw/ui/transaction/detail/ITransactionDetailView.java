package com.j7arsen.pw.ui.transaction.detail;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.j7arsen.pw.domain.model.TransTokenModel;

/**
 * Created by arsen on 01.03.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface ITransactionDetailView extends MvpView{

    void fillTransDetailData(String userName, TransTokenModel transTokenModel);

}
