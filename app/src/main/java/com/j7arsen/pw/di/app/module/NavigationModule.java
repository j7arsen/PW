package com.j7arsen.pw.di.app.module;

import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.di.app.qualifier.Local;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 25.02.2018.
 */
@Module
public class NavigationModule {

    private Cicerone<Router> localCicerone;
    private Cicerone<Router> globalCicerone;

    public NavigationModule() {
        localCicerone = Cicerone.create();
        globalCicerone = Cicerone.create();
    }

    @Provides
    @Singleton
    @Local
    Router provideLocalRouter() {
        return localCicerone.getRouter();
    }

    @Provides
    @Singleton
    @Local
    NavigatorHolder provideLocalNavigatorHolder() {
        return localCicerone.getNavigatorHolder();
    }

    @Provides
    @Singleton
    @Global
    Router provideGlobalRouter() {
        return globalCicerone.getRouter();
    }

    @Provides
    @Singleton
    @Global
    NavigatorHolder provideGlobalNavigatorHolder() {
        return globalCicerone.getNavigatorHolder();
    }

}
