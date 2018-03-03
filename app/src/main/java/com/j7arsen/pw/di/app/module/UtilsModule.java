package com.j7arsen.pw.di.app.module;

import android.content.Context;

import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.AppUtils;
import com.j7arsen.pw.utils.screencreator.IScreenCreator;
import com.j7arsen.pw.utils.screencreator.ScreenCreator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 26.02.2018.
 */
@Module
public class UtilsModule {

    @Provides
    @Singleton
    public ResUtils provideResUtils(Context context) {
        return new ResUtils(context);
    }

    @Provides
    @Singleton
    public AppUtils provideUtils() {
        return new AppUtils();
    }

    @Provides
    @Singleton
    public IScreenCreator provideScreenCreator() {
        return new ScreenCreator();
    }

}
