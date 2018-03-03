package com.j7arsen.pw.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.j7arsen.pw.R;
import com.j7arsen.pw.utils.IToolbarBackPressListener;

import butterknife.BindView;

/**
 * Created by arsen on 25.02.2018.
 */

public abstract class BaseToolbarFragment extends BaseFragment{

    @BindView(R.id.navigation_bar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitleTextView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    protected void initDefaultToolbar(View toolbar) {
        if (toolbar != null) {
            mToolbar = (Toolbar) toolbar.findViewById(R.id.navigation_bar);
            mTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        }
    }

    protected void initDefaultBackToolbar(View toolbar, IToolbarBackPressListener toolbarBackPressListener) {
        initDefaultToolbar(toolbar);
        if (toolbar != null) {
            mActivity.setSupportActionBar(mToolbar);
            if (mActivity.getSupportActionBar() != null) {
                mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
                mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(toolbarBackPressListener != null){
                        toolbarBackPressListener.toolbarBackPressed();
                    }
                }
            });

        }
    }

    public void setTitle(CharSequence title) {
        if (mTitleTextView != null && title != null) {
            mTitleTextView.setText(title);
        }
    }

    public void setTitle(int titleId) {
        if (mTitleTextView != null) {
            mTitleTextView.setText(null);
        }
    }

    protected abstract void initToolbar();

}
