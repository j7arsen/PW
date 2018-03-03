package com.j7arsen.pw.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.j7arsen.pw.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by arsen on 25.02.2018.
 */

public abstract class BaseDialogFragment extends MvpAppCompatDialogFragment {

    private Unbinder mUnbinder;

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (Activity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            mActivity = activity;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(getLayout(), null);
        mUnbinder = ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Theme_Dialog);
        builder.setView(view);

        return builder.create();
    }

    protected abstract int getLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
    }

}
