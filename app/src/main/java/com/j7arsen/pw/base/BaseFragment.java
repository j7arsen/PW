package com.j7arsen.pw.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by arsen on 25.02.2018.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

    private Unbinder mUnbinder;

    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            mActivity = (BaseActivity) activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mActivity = null;
    }

}
