package com.j7arsen.pw.ui.auth.main;

import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.utils.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 28.02.2018.
 */

public class AuthPresenter extends BasePresenter<IAuthView>{

    @Inject
    @Global
    Router router;

    public AuthPresenter() {
        ComponentManager.getInstance().getAuthComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        openMainScreen();
    }

    public void openMainScreen() {
        router.newRootScreen(Screens.SIGN_IN_SCREEN);
    }

    @Override
    public void onDestroy() {
        ComponentManager.getInstance().destroyAuthComponent();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }
}
