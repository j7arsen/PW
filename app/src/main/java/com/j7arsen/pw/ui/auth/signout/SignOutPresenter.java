package com.j7arsen.pw.ui.auth.signout;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.manager.DataManager;

import javax.inject.Inject;

/**
 * Created by arsen on 28.02.2018.
 */
@InjectViewState
public class SignOutPresenter extends MvpPresenter<ISignOutView> {

    @Inject
    DataManager dataManager;

    public SignOutPresenter() {
        ComponentManager.getInstance().getMainComponent().inject(this);
    }

    public void signOut() {
        dataManager.logout();
        getViewState().signOut();
    }

}
