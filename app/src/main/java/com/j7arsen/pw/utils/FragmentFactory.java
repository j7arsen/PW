package com.j7arsen.pw.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.ui.auth.signin.SignInFragment;
import com.j7arsen.pw.ui.auth.signup.SignUpFragment;
import com.j7arsen.pw.ui.transfer.success.TransferSuccessFragment;
import com.j7arsen.pw.ui.transaction.detail.TransactionDetailFragment;
import com.j7arsen.pw.ui.transaction.list.TransactionListFragment;
import com.j7arsen.pw.ui.main.fragment.MainFragment;
import com.j7arsen.pw.ui.transfer.transfer.TransferFragment;

/**
 * Created by arsen on 26.02.2018.
 */

public class FragmentFactory {

    private FragmentFactory() {
    }

    public static Fragment getFragmentByKey(@NonNull final String key, final Object data) {
        switch (key) {
            case Screens.SIGN_IN_SCREEN:
                return SignInFragment.newInstance();
            case Screens.SIGN_UP_SCREEN:
                return SignUpFragment.newInstance();
            case Screens.MAIN_SCREEN:
                return MainFragment.newInstance();
            case Screens.TRANSFER_SCREEN:
                TransTokenModel transTokenModelTransfer = (TransTokenModel) data;
                return TransferFragment.newInstance(transTokenModelTransfer);
            case Screens.TRANSFER_SUCCESS_SCREEN:
                return TransferSuccessFragment.newInstance();
            case Screens.TRANSACTION_LIST_SCREEN:
                return TransactionListFragment.newInstance();
            case Screens.TRANSACTION_DETAIL_SCREEN:
                TransTokenModel transTokenModel = (TransTokenModel) data;
                return TransactionDetailFragment.newInstance(transTokenModel);
            default:
                return null;
        }
    }

}
