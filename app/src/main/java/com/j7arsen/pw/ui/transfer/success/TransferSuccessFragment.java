package com.j7arsen.pw.ui.transfer.success;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.j7arsen.pw.R;
import com.j7arsen.pw.base.BaseToolbarFragment;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.utils.IBackButtonListener;
import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.Screens;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 01.03.2018.
 */

public class TransferSuccessFragment extends BaseToolbarFragment implements IBackButtonListener {

    @BindView(R.id.navigation_bar)
    Toolbar navigationBar;
    @BindView(R.id.tv_user_balance)
    TextView tvUserBalance;
    @BindView(R.id.btn_success)
    Button btnSuccess;

    @Inject
    ResUtils resUtils;

    @Inject
    @Global
    Router router;

    public static TransferSuccessFragment newInstance() {
        TransferSuccessFragment transferSuccessFragment = new TransferSuccessFragment();
        return transferSuccessFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getTransferComponent().inject(this);
    }

    @OnClick(R.id.btn_success)
    public void onViewClicked() {
        exitWithResult();
    }

    private void exitWithResult(){
        router.exitWithResult(Screens.TRANSFER_RESULT, null);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_transfer_success;
    }

    @Override
    protected void initToolbar() {
        initDefaultBackToolbar(navigationBar, () -> exitWithResult());
        setTitle(resUtils.getString(R.string.transfer_operation_success_screen_title));
    }

    @Override
    public boolean onBackPressed() {
        exitWithResult();
        return true;
    }
}
