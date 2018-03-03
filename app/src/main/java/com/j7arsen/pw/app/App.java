package com.j7arsen.pw.app;

import android.app.Application;

import com.j7arsen.pw.di.ComponentManager;


/**
 * Created by arsen on 25.02.2018.
 */

public class App extends Application {

    public static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ComponentManager.getInstance().initAppComponent(this);
    }

}
