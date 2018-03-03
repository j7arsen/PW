package com.j7arsen.pw.ui.main;

import com.j7arsen.pw.base.BasePresenter;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.utils.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 28.02.2018.
 */

public class MainActivityPresenter extends BasePresenter<IMainActivityView> {

    @Inject
    @Global
    Router router;

    public MainActivityPresenter() {
        ComponentManager.getInstance().getMainComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        openMainScreen();
    }

    public void openMainScreen() {
        router.newRootScreen(Screens.MAIN_SCREEN);
    }

    @Override
    public void onDestroy() {
        ComponentManager.getInstance().destroyMainComponent();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }

}
