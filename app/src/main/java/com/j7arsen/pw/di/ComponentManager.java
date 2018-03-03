package com.j7arsen.pw.di;

import android.support.annotation.VisibleForTesting;

import com.j7arsen.pw.app.App;
import com.j7arsen.pw.di.app.AppComponent;
import com.j7arsen.pw.di.app.DaggerAppComponent;
import com.j7arsen.pw.di.app.module.ApplicationModule;
import com.j7arsen.pw.di.auth.AuthComponent;
import com.j7arsen.pw.di.transaction.TransactionComponent;
import com.j7arsen.pw.di.main.MainComponent;
import com.j7arsen.pw.di.transfer.TransferComponent;

/**
 * Created by arsen on 25.02.2018.
 */

public class ComponentManager {

    private static ComponentManager componentManager;

    private AppComponent appComponent;
    private AuthComponent authComponent;
    private MainComponent mainComponent;
    private TransferComponent transferComponent;
    private TransactionComponent transactionComponent;

    public static ComponentManager getInstance() {
        if (componentManager == null) {
            componentManager = new ComponentManager();
        }
        return componentManager;
    }

    public void initAppComponent(App app) {
        appComponent = DaggerAppComponent.builder().applicationModule(new ApplicationModule(app)).build();
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent){
        this.appComponent = appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AuthComponent getAuthComponent() {
        if (authComponent == null) {
            authComponent = getAppComponent().authComponentBuilder().build();
        }
        return authComponent;
    }

    public MainComponent getMainComponent() {
        if (mainComponent == null) {
            mainComponent = getAppComponent().mainComponentBuilder().build();
        }
        return mainComponent;
    }

    public TransferComponent getTransferComponent() {
        if (transferComponent == null) {
            transferComponent = getAppComponent().transferComponentBuilder().build();
        }
        return transferComponent;
    }

    public TransactionComponent getTransactionComponent() {
        if (transactionComponent == null) {
            transactionComponent = getAppComponent().transactionComponentBuilder().build();
        }
        return transactionComponent;
    }

    public void destroyAuthComponent() {
        authComponent = null;
    }

    public void destroyMainComponent() {
        mainComponent = null;
    }

    public void destroyTransferComponent() {
        transferComponent = null;
    }

    public void destroyTransactionComponent() {
        transactionComponent = null;
    }

}
