package com.j7arsen.pw.ui.transfer.transfer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.j7arsen.pw.R;
import com.j7arsen.pw.base.BaseToolbarFragment;
import com.j7arsen.pw.data.model.net.transfer.RecipientEntity;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.utils.AppUtils;
import com.j7arsen.pw.utils.IBackButtonListener;
import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.Screens;
import com.j7arsen.pw.utils.screencreator.IScreenCreator;
import com.j7arsen.pw.utils.view.EnableButton;
import com.j7arsen.pw.utils.view.progress.ProgressDialogManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by arsen on 01.03.2018.
 */

public class TransferFragment extends BaseToolbarFragment implements ITransferView, IBackButtonListener {

    public static final String TRANS_TOKEN_MODEL = "TransferFragment.TRANS_TOKEN_MODEL";

    private static final String TAG = "TransferFragment";

    @BindView(R.id.navigation_bar)
    Toolbar navigationBar;
    @BindView(R.id.tv_user_search_title)
    TextView tvUserSearchTitle;
    @BindView(R.id.atv_user)
    AutoCompleteTextView atvUser;
    @BindView(R.id.clear_text_button)
    ImageView clearTextButton;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.tv_amount_limit)
    TextView tvAmountLimit;
    @BindView(R.id.btn_transfer)
    EnableButton btnPay;

    @Inject
    ResUtils resUtils;

    @Inject
    AppUtils appUtils;

    @Inject
    IScreenCreator screenCreator;

    @InjectPresenter
    TransferPresenter presenter;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @ProvidePresenter
    TransferPresenter provideTransferPresenter() {
        return new TransferPresenter(resUtils, (TransTokenModel) getArguments().getSerializable(TRANS_TOKEN_MODEL));
    }

    public static TransferFragment newInstance(TransTokenModel transTokenModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TRANS_TOKEN_MODEL, transTokenModel);
        TransferFragment transferFragment = new TransferFragment();
        transferFragment.setArguments(bundle);
        return transferFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getTransferComponent().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    private void setListeners(){
        presenter.setTextWatcher(getInputTextWatcherObservable(atvUser));
        addOnAutoCompleteTextViewItemClickedSubscriber(atvUser);
        compositeDisposable.add(appUtils.getEmptyWatcherObservable(etAmount).subscribe(enable -> enablePayButton(enable)));
    }

    public static Observable<String> getInputTextWatcherObservable(@NonNull final AutoCompleteTextView autocompleteText) {

        final PublishSubject<String> subject = PublishSubject.create();

        autocompleteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                subject.onNext(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return subject;
    }

    public static Observable<Integer> getItemClickObservable(@NonNull final AutoCompleteTextView autocompleteText) {

        final PublishSubject<Integer> subject = PublishSubject.create();

        autocompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                subject.onNext(i);
            }
        });

        return subject;
    }

    @Override
    public void initAutocompleteAdapter(Observable<List<RecipientEntity>> recipientLisObservable) {
        compositeDisposable.add(
                recipientLisObservable.subscribe(
                        userList -> {
                            ArrayAdapter<RecipientEntity> itemsAdapter = new ArrayAdapter<>(mActivity,
                                    android.R.layout.simple_list_item_1, userList);
                            atvUser.setAdapter(itemsAdapter);
                            String enteredText = atvUser.getText().toString();
                            if (userList.size() >= 1 && enteredText.equals(userList.get(0).getName())) {
                                atvUser.dismissDropDown();
                            } else {
                                atvUser.showDropDown();
                            }
                        },
                        e -> Log.e(TAG, "onError", e),
                        () -> Log.i(TAG, "onCompleted")));
    }

    private void addOnAutoCompleteTextViewItemClickedSubscriber(final AutoCompleteTextView autoCompleteTextView) {
        Observable<RecipientEntity> adapterViewItemClickEventObservable =
                getItemClickObservable(autoCompleteTextView).map(adapterViewItemClickEvent -> {
                    RecipientEntity item = (RecipientEntity) autoCompleteTextView.getAdapter()
                            .getItem(adapterViewItemClickEvent);
                    return item;
                })
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .retry();

        compositeDisposable.add(
                adapterViewItemClickEventObservable.subscribe(
                        recipientEntity -> {
                            presenter.setRecipientName(recipientEntity.getName());
                        },
                        throwable -> Log.e(TAG, "onError", throwable),
                        () -> Log.i(TAG, "onCompleted")));
    }


    @OnClick({R.id.clear_text_button, R.id.btn_transfer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_text_button:
                atvUser.setText("");
                etAmount.setText("");
                presenter.setRecipientName(null);
                break;
            case R.id.btn_transfer:
                appUtils.hideKeyboard(mActivity);
                presenter.pay(etAmount.getText().toString());
                break;
        }
    }

    @Override
    public void initTransactionDate(long amount, String recipientName) {
        etAmount.setText(String.valueOf(amount));
        atvUser.setText(recipientName);
        atvUser.setSelection(recipientName.length());
    }

    @Override
    public void enableAmountField(boolean isEnable) {
        etAmount.setEnabled(isEnable);
    }

    private void enablePayButton(boolean isEnable) {
        btnPay.enable(isEnable);
    }

    @Override
    public void showBalanceLimitError(String error) {
        tvAmountLimit.setVisibility(View.VISIBLE);
        tvAmountLimit.setText(error);
        enablePayButton(false);
    }

    @Override
    public void hideBalanceLimitError() {
        tvAmountLimit.setVisibility(View.GONE);
        enablePayButton(true);
    }

    @Override
    public void showProgress() {
        ProgressDialogManager.getInstance().startLoading(this);
    }

    @Override
    public void hideProgress() {
        ProgressDialogManager.getInstance().completeLoading();
    }

    @Override
    public void showError(String message) {
        ProgressDialogManager.getInstance().errorLoading(s -> presenter.errorCancel(), message);
    }

    @Override
    public void hideError() {
        ProgressDialogManager.getInstance().unsubscribe();
    }

    @Override
    public void successTransfer() {
        mActivity.setResult(Screens.RESULT_TRANSFER_SCREEN);
        mActivity.finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_transfer;
    }

    @Override
    protected void initToolbar() {
        initDefaultBackToolbar(navigationBar, () -> presenter.onBackPressed());
        setTitle(resUtils.getString(R.string.transfer_screen_title));
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

}
