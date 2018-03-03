package com.j7arsen.pw.ui.splash;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.j7arsen.pw.ui.main.MainActivity;
import com.j7arsen.pw.R;
import com.j7arsen.pw.base.BaseActivity;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.ui.auth.main.AuthActivity;
import com.j7arsen.pw.utils.screencreator.IScreenCreator;

import javax.inject.Inject;

/**
 * Created by arsen on 28.02.2018.
 */

public class SplashActivity extends BaseActivity implements ISplashView {

    @Inject
    IScreenCreator screenCreator;

    @InjectPresenter
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getAppComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        screenCreator.startActivity(this, isAuthorized ? MainActivity.class : AuthActivity.class);
        finish();
    }
}
