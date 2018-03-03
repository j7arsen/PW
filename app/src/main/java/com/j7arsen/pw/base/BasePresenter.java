package com.j7arsen.pw.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

/**
 * Created by arsen on 25.02.2018.
 */

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    public abstract void onBackPressed();
}
