package com.j7arsen.pw.ui.transfer.transfer;

import com.arellomobile.mvp.InjectViewState;
import com.j7arsen.pw.R;
import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.data.api.ApiService;
import com.j7arsen.pw.data.api.RequestField;
import com.j7arsen.pw.data.model.mapper.IObjectListModelMapper;
import com.j7arsen.pw.data.model.net.transfer.RecipientEntity;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.domain.interactor.TransferUseCase;
import com.j7arsen.pw.domain.model.RecipientModel;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.domain.model.requestbody.RecipientFilter;
import com.j7arsen.pw.domain.model.requestbody.TransactionObject;
import com.j7arsen.pw.manager.DataManager;
import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.Screens;
import com.j7arsen.pw.utils.helper.ErrorHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.result.ResultListener;

/**
 * Created by arsen on 01.03.2018.
 */
@InjectViewState
public class TransferPresenter extends BasePresenter<ITransferView> {

    private static final long DELAY_IN_MILLIS = 500;
    public static final int MIN_LENGTH_TO_START = 2;

    @Inject
    @Global
    Router router;

    @Inject
    ErrorHandler errorHandler;

    @Inject
    DataManager dataManager;

    @Inject
    IObjectListModelMapper<RecipientEntity, RecipientModel> mapper;

    @Inject
    ApiService apiService;

    @Inject
    TransferUseCase transferUseCase;

    private ResUtils resUtils;

    private String recipientName;
    private long amount;

    private TransTokenModel transTokenModel;

    public TransferPresenter(ResUtils resUtils, TransTokenModel transTokenModel) {
        ComponentManager.getInstance().getTransferComponent().inject(this);
        setResultListener();
        this.resUtils = resUtils;
        this.transTokenModel = transTokenModel;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        initView();
    }

    private void initView() {
        if (transTokenModel != null) {
            recipientName = transTokenModel.getUsername();
            setRecipientName(recipientName);
            getViewState().initTransactionDate(Math.abs(transTokenModel.getAmount()), recipientName);
        }
    }

    public void errorCancel() {
        getViewState().hideError();
    }

    public void setTextWatcher(Observable inputTextWatcher) {
        addOnAutoCompleteTextViewTextChangedObserver(inputTextWatcher);
    }

    private void addOnAutoCompleteTextViewTextChangedObserver(Observable inputTextWatcher) {
        Observable<List<RecipientEntity>> autocompleteResponseObservable =
                inputTextWatcher
                        .debounce(DELAY_IN_MILLIS, TimeUnit.MILLISECONDS)
                        .map(textViewTextChangeEvent -> textViewTextChangeEvent.toString())
                        .filter(s -> s.toString().length() >= MIN_LENGTH_TO_START)
                        .observeOn(Schedulers.io())
                        .switchMap(s -> apiService.searchUserList(RequestField.getHeader(dataManager.getTokenModel()), new RecipientFilter(s.toString())))
                        .observeOn(AndroidSchedulers.mainThread())
                        .retry();
        getViewState().initAutocompleteAdapter(autocompleteResponseObservable);
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
        getViewState().enableAmountField(recipientName != null);
    }

    public void pay(String amount) {
        this.amount = Long.parseLong(amount);
        if (this.amount > dataManager.getUserInfoModel().getBalance()) {
            getViewState().showBalanceLimitError(resUtils.getString(R.string.transfer_limit));
        } else {
            if(this.amount <= 0){
                getViewState().showBalanceLimitError(resUtils.getString(R.string.transfer_zero));
            } else {
                getViewState().hideBalanceLimitError();
                transferRequest(amount);
            }
        }
    }

    private void transferRequest(String amount) {
        transferUseCase.execute(new TransferUseCase.Params(dataManager.getTokenModel(), new TransactionObject(recipientName, amount)), new DisposableSingleObserver<TransTokenModel>() {
            @Override
            protected void onStart() {
                getViewState().showProgress();
            }

            @Override
            public void onSuccess(TransTokenModel transTokenModel) {
                getViewState().hideProgress();
                router.navigateTo(Screens.TRANSFER_SUCCESS_SCREEN);
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(errorHandler.getError(e));
            }
        });
    }

    private void setResultListener() {
        router.setResultListener(Screens.TRANSFER_RESULT, new ResultListener() {
            @Override
            public void onResult(Object resultData) {
                getViewState().successTransfer();
            }
        });
    }

    private void removeResultListener() {
        router.removeResultListener(Screens.TRANSFER_RESULT);
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
