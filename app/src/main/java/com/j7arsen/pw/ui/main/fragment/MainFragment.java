package com.j7arsen.pw.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.j7arsen.pw.R;
import com.j7arsen.pw.base.BaseToolbarFragment;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.domain.model.UserInfoModel;
import com.j7arsen.pw.ui.auth.main.AuthActivity;
import com.j7arsen.pw.ui.auth.signout.ISignOutView;
import com.j7arsen.pw.ui.auth.signout.SignOutPresenter;
import com.j7arsen.pw.ui.transfer.main.TransferActivity;
import com.j7arsen.pw.ui.transaction.main.TransactionActivity;
import com.j7arsen.pw.utils.IBackButtonListener;
import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.Screens;
import com.j7arsen.pw.utils.executor.UpdateBalanceEvent;
import com.j7arsen.pw.utils.screencreator.IScreenCreator;
import com.j7arsen.pw.utils.view.progress.ProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by arsen on 28.02.2018.
 */

public class MainFragment extends BaseToolbarFragment implements IMainView, ISignOutView, IBackButtonListener, ProgressView.OnRetryListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.navigation_bar)
    Toolbar navigationBar;
    @BindView(R.id.tv_user_name_title)
    TextView tvUserNameTitle;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.rl_user_name)
    RelativeLayout rlUserName;
    @BindView(R.id.tv_user_email_title)
    TextView tvUserEmailTitle;
    @BindView(R.id.tv_user_email)
    TextView tvUserEmail;
    @BindView(R.id.rl_user_email)
    RelativeLayout rlUserEmail;
    @BindView(R.id.tv_user_balance)
    TextView tvUserBalance;
    @BindView(R.id.srl_main_container)
    SwipeRefreshLayout srlMainContainer;
    @BindView(R.id.pv_load)
    ProgressView pvLoad;

    @Inject
    ResUtils resUtils;

    @Inject
    IScreenCreator screenCreator;

    @InjectPresenter
    MainPresenter presenter;

    @InjectPresenter
    SignOutPresenter signOutPresenter;

    public static MainFragment newInstance() {
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setHasOptionsMenu(true);
        ComponentManager.getInstance().getMainComponent().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    private void setListeners() {
        pvLoad.setOnRetryListener(this);
        srlMainContainer.setOnRefreshListener(this);
    }

    @OnClick({R.id.btn_transfer, R.id.btn_transfer_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer:
                screenCreator.startActivity(this, mActivity, TransferActivity.class, Screens.START_TRANSFER_SCREEN);
                break;
            case R.id.btn_transfer_history:
                screenCreator.startActivity(this, mActivity, TransactionActivity.class, Screens.START_TRANSACTION_SCREEN);
                break;
        }
    }

    @Override
    public void showProgress() {
        if (isAdded()) {
            srlMainContainer.setVisibility(View.GONE);
            pvLoad.startLoading();
        }
    }

    @Override
    public void hideProgress() {
        if (isAdded()) {
            srlMainContainer.setVisibility(View.VISIBLE);
            pvLoad.completeLoading();
            goneSrl();
        }
    }

    @Override
    public void showError(String error) {
        if (isAdded()) {
            srlMainContainer.setVisibility(View.GONE);
            pvLoad.errorLoading(error);
            goneSrl();
        }
    }

    @Override
    public void showUserIfo(UserInfoModel userInfoModel) {
        if (userInfoModel != null) {
            tvUserName.setText(userInfoModel.getName());
            tvUserEmail.setText(userInfoModel.getEmail());
            tvUserBalance.setText(String.valueOf(userInfoModel.getBalance()));
        }
    }

    @Override
    public void signOut() {
        mActivity.finish();
        screenCreator.startActivity(mActivity,  AuthActivity.class);
    }

    @Override
    public void onRetry() {
        presenter.loadUserInfoData(false);
    }

    @Override
    public void onRefresh() {
        presenter.loadUserInfoData(true);
    }

    private void goneSrl() {
        srlMainContainer.post(new Runnable() {
            @Override
            public void run() {
                if (isAdded()) {
                    srlMainContainer.setRefreshing(false);
                }
            }
        });
    }

    @Subscribe
    public void onMessageEvent(UpdateBalanceEvent event) {
        presenter.loadUserInfoData(false);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initToolbar() {
        initDefaultToolbar(navigationBar);
        setTitle(resUtils.getString(R.string.main_screen_title));
        navigationBar.inflateMenu(R.menu.main_menu);
        navigationBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_sign_out:
                        signOutPresenter.signOut();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Screens.START_TRANSFER_SCREEN:
                if (resultCode == Screens.RESULT_TRANSFER_SCREEN) {
                    presenter.loadUserInfoData(false);
                }
                break;
        }
    }
}
