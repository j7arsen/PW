package com.j7arsen.pw.di.app;

import com.j7arsen.pw.di.app.module.ApplicationModule;
import com.j7arsen.pw.di.app.module.NavigationModule;
import com.j7arsen.pw.di.app.module.NetModule;
import com.j7arsen.pw.di.app.module.ThreadingModule;
import com.j7arsen.pw.di.app.module.UtilsModule;
import com.j7arsen.pw.di.auth.AuthComponent;
import com.j7arsen.pw.di.transaction.TransactionComponent;
import com.j7arsen.pw.di.main.MainComponent;
import com.j7arsen.pw.di.transfer.TransferComponent;
import com.j7arsen.pw.ui.splash.SplashActivity;
import com.j7arsen.pw.ui.splash.SplashPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by arsen on 25.02.2018.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ThreadingModule.class,
        UtilsModule.class,
        NetModule.class,
        NavigationModule.class})
public interface AppComponent {

    AuthComponent.Builder authComponentBuilder();

    MainComponent.Builder mainComponentBuilder();

    TransferComponent.Builder transferComponentBuilder();

    TransactionComponent.Builder transactionComponentBuilder();

    void inject(SplashActivity splashActivity);

    void inject(SplashPresenter splashPresenter);

}
