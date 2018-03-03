package com.j7arsen.pw.base;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpDelegate;

/**
 * Created by arsen on 25.02.2018.
 */

public abstract class BaseMvpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private MvpDelegate<? extends BaseMvpAdapter> mMvpDelegate;
    private MvpDelegate<?> mParentDelegate;
    private String mChildId;

    public BaseMvpAdapter(MvpDelegate<?> parentDelegate, String childId) {
        mParentDelegate = parentDelegate;
        mChildId = childId;

        getMvpDelegate().onCreate();
    }

    public MvpDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new MvpDelegate<>(this);
            mMvpDelegate.setParentDelegate(mParentDelegate, mChildId);

        }
        return mMvpDelegate;
    }

}
