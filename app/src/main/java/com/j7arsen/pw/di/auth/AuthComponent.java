package com.j7arsen.pw.di.auth;

import com.j7arsen.pw.di.auth.module.AuthRepositoryModule;
import com.j7arsen.pw.di.auth.module.AuthUseCaseModule;
import com.j7arsen.pw.ui.auth.main.AuthActivity;
import com.j7arsen.pw.ui.auth.main.AuthPresenter;
import com.j7arsen.pw.ui.auth.signin.SignInFragment;
import com.j7arsen.pw.ui.auth.signin.SignInPresenter;
import com.j7arsen.pw.ui.auth.signup.SignUpFragment;
import com.j7arsen.pw.ui.auth.signup.SignUpPresenter;

import dagger.Subcomponent;

/**
 * Created by arsen on 26.02.2018.
 */
@AuthScope
@Subcomponent(modules = {
        AuthRepositoryModule.class,
        AuthUseCaseModule.class})
public interface AuthComponent {

    void inject(AuthActivity authActivity);

    void inject(AuthPresenter authPresenter);

    void inject(SignInFragment signInFragment);

    void inject(SignInPresenter signInPresenter);

    void inject(SignUpFragment signUpFragment);

    void inject(SignUpPresenter signUpPresenter);

    @Subcomponent.Builder
    interface Builder {
        AuthComponent build();
    }

}
