package com.j7arsen.pw.di.main;

import com.j7arsen.pw.di.main.module.MainRepositoryModule;
import com.j7arsen.pw.di.main.module.MainUseCaseModule;
import com.j7arsen.pw.ui.auth.signout.SignOutPresenter;
import com.j7arsen.pw.ui.main.MainActivity;
import com.j7arsen.pw.ui.main.MainActivityPresenter;
import com.j7arsen.pw.ui.main.fragment.MainFragment;
import com.j7arsen.pw.ui.main.fragment.MainPresenter;

import dagger.Subcomponent;

/**
 * Created by arsen on 28.02.2018.
 */
@MainScope
@Subcomponent(modules = {
        MainRepositoryModule.class,
        MainUseCaseModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(MainActivityPresenter mainActivityPresenter);

    void inject(SignOutPresenter signOutPresenter);

    void inject(MainFragment mainFragment);

    void inject(MainPresenter mainPresenter);


    @Subcomponent.Builder
    interface Builder {
        MainComponent build();
    }

}
