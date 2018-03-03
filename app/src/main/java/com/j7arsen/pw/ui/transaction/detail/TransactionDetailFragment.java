package com.j7arsen.pw.ui.transaction.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.j7arsen.pw.R;
import com.j7arsen.pw.base.BaseToolbarFragment;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.ui.transfer.main.TransferActivity;
import com.j7arsen.pw.utils.FormatterDate;
import com.j7arsen.pw.utils.IBackButtonListener;
import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.Screens;
import com.j7arsen.pw.utils.screencreator.IScreenCreator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by arsen on 01.03.2018.
 */

public class TransactionDetailFragment extends BaseToolbarFragment implements ITransactionDetailView, IBackButtonListener {

    private static final String TRANS_TOKEN_MODEL = "TransactionDetailFragment.TRANS_TOKEN_MODEL";

    @BindView(R.id.tv_transaction_correspondetn)
    TextView tvTransactionCorrespondent;
    @BindView(R.id.tv_transaction_date)
    TextView tvTransactionDate;
    @BindView(R.id.tv_transaction_amount)
    TextView tvTransactionAmount;
    @BindView(R.id.tv_transaction_balance)
    TextView tvTransactionBalance;
    @BindView(R.id.btn_transfer)
    Button btnTransfer;

    @BindView(R.id.navigation_bar)
    Toolbar navigationBar;

    @Inject
    ResUtils resUtils;

    @Inject
    IScreenCreator screenCreator;

    @InjectPresenter
    TransactionDetailPresenter presenter;

    @ProvidePresenter
    TransactionDetailPresenter provideTransactionDetailPresenter() {
        return new TransactionDetailPresenter((TransTokenModel) getArguments().getSerializable(TRANS_TOKEN_MODEL));
    }

    public static TransactionDetailFragment newInstance(TransTokenModel transTokenModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TRANS_TOKEN_MODEL, transTokenModel);
        TransactionDetailFragment extractDetailFragment = new TransactionDetailFragment();
        extractDetailFragment.setArguments(bundle);
        return extractDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getTransactionComponent().inject(this);
    }

    @Override
    public void fillTransDetailData(String userName, TransTokenModel transTokenModel) {
        if (transTokenModel != null) {
            tvTransactionCorrespondent.setText(transTokenModel.getUsername());
            tvTransactionDate.setText(FormatterDate.formatServerDate(transTokenModel.getDate()));
            tvTransactionAmount.setText(String.valueOf(Math.abs(transTokenModel.getAmount())));
            tvTransactionBalance.setText(String.valueOf(transTokenModel.getBalance()));
            if (transTokenModel.getUsername().equalsIgnoreCase(userName)) {
                //income transaction
                btnTransfer.setVisibility(View.GONE);
            } else {
                btnTransfer.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick(R.id.btn_transfer)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TransferActivity.TRANS_TOKEN_MODEL, getArguments().getSerializable(TRANS_TOKEN_MODEL));
        screenCreator.startActivity(this, mActivity, TransferActivity.class, bundle, Screens.START_TRANSFER_SCREEN);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_transaction_detail;
    }

    @Override
    protected void initToolbar() {
        initDefaultBackToolbar(navigationBar, () -> presenter.onBackPressed());
        setTitle(resUtils.getString(R.string.transaction_detail_screen_title));
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Screens.START_TRANSFER_SCREEN:
                if (resultCode == Screens.RESULT_TRANSFER_SCREEN) {
                    presenter.transferSuccess();
                }
                break;
        }
    }

}
