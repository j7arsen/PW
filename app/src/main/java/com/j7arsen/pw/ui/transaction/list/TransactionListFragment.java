package com.j7arsen.pw.ui.transaction.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.j7arsen.pw.R;
import com.j7arsen.pw.base.BaseToolbarFragment;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.ui.transaction.list.adapter.TransactionAdapter;
import com.j7arsen.pw.utils.IBackButtonListener;
import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.view.EmptyView;
import com.j7arsen.pw.utils.view.progress.ProgressView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by arsen on 01.03.2018.
 */

public class TransactionListFragment extends BaseToolbarFragment implements ITransactionListView, IBackButtonListener, ProgressView.OnRetryListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.navigation_bar)
    Toolbar navigationBar;
    @BindView(R.id.rv_transaction)
    RecyclerView rvTransaction;
    @BindView(R.id.ev_transaction)
    EmptyView evTransaction;
    @BindView(R.id.srl_transaction_container)
    SwipeRefreshLayout srlTransactionContainer;
    @BindView(R.id.pv_load)
    ProgressView pvLoad;

    @Inject
    ResUtils resUtils;

    @InjectPresenter
    TransactionListPresenter presenter;

    private TransactionAdapter transactionAdapter;

    public static TransactionListFragment newInstance() {
        TransactionListFragment extractListFragment = new TransactionListFragment();
        return extractListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getTransactionComponent().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapters();
        setListeners();
    }

    private void initAdapters(){
        transactionAdapter = new TransactionAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        rvTransaction.setLayoutManager(layoutManager);
        rvTransaction.setAdapter(transactionAdapter);
        transactionAdapter.setClickListener(transTokenModel -> presenter.openDetailScreen(transTokenModel));
    }

    private void setListeners() {
        pvLoad.setOnRetryListener(this);
        srlTransactionContainer.setOnRefreshListener(this);
    }

    @Override
    public void showList(List<TransTokenModel> transTokenModelList) {
        transactionAdapter.swap(transTokenModelList);
        if (!presenter.isInRestoreState(this)) {
            rvTransaction.scheduleLayoutAnimation();
        } else {
            rvTransaction.setLayoutAnimation(null);
        }
    }

    @Override
    public void showProgress() {
        if (isAdded()) {
            srlTransactionContainer.setVisibility(View.GONE);
            pvLoad.startLoading();
        }
    }

    @Override
    public void hideProgress() {
        if (isAdded()) {
            srlTransactionContainer.setVisibility(View.VISIBLE);
            pvLoad.completeLoading();
            goneSrl();
        }
    }

    @Override
    public void showError(String error) {
        if (isAdded()) {
            srlTransactionContainer.setVisibility(View.GONE);
            pvLoad.errorLoading(error);
            goneSrl();
        }
    }

    @Override
    public void showEmptyListView() {
        evTransaction.show();
    }

    @Override
    public void hideEmptyListView() {
        evTransaction.hide();
    }

    @Override
    public void onRefresh() {
        presenter.loadTransaction(true);
    }

    @Override
    public void onRetry() {
        presenter.loadTransaction(false);
    }

    private void goneSrl() {
        srlTransactionContainer.post(new Runnable() {
            @Override
            public void run() {
                if (isAdded()) {
                    srlTransactionContainer.setRefreshing(false);
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_transaction_list;
    }

    @Override
    protected void initToolbar() {
        initDefaultBackToolbar(navigationBar, () -> presenter.onBackPressed());
        setTitle(resUtils.getString(R.string.transaction_screen_title));
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }
}
