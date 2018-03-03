package com.j7arsen.pw.ui.transfer.transfer;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.j7arsen.pw.data.model.net.transfer.RecipientEntity;

import java.util.List;

/**
 * Created by arsen on 01.03.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface ITransferView extends MvpView{

    void showProgress();

    void hideProgress();

    void showError(String message);

    void hideError();

    void showBalanceLimitError(String message);

    void hideBalanceLimitError();

    void enableAmountField(boolean isEnable);

    void initTransactionDate(long amount, String recipientName);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void initAutocompleteAdapter(io.reactivex.Observable<List<RecipientEntity>> recipientLisObservable);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void successTransfer();

}
