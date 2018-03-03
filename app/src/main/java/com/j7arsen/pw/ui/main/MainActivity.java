package com.j7arsen.pw.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.j7arsen.pw.R;
import com.j7arsen.pw.base.BaseContainerActvitiy;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.ui.auth.main.AuthActivity;
import com.j7arsen.pw.ui.main.fragment.MainFragment;
import com.j7arsen.pw.utils.FragmentFactory;
import com.j7arsen.pw.utils.IBackButtonListener;
import com.j7arsen.pw.utils.screencreator.IScreenCreator;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;

public class MainActivity extends BaseContainerActvitiy implements IMainActivityView {

    @Inject
    @Global
    NavigatorHolder navigatorHolder;

    @Inject
    IScreenCreator screenCreator;

    @InjectPresenter
    MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getMainComponent().inject(this);
    }

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fl_container) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            return FragmentFactory.getFragmentByKey(screenKey, data);
        }

        @Override
        protected void showSystemMessage(String message) {

        }

        @Override
        protected void exit() {
            finish();
        }

        @Override
        protected void setupFragmentTransactionAnimation(Command command, Fragment currentFragment, Fragment nextFragment, FragmentTransaction fragmentTransaction) {
            if(!(nextFragment instanceof MainFragment)) {
                fragmentTransaction.setCustomAnimations(R.anim.screen_in, R.anim.screen_out, R.anim.screen_back_in, R.anim.screen_back_out);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (fragment != null
                && fragment instanceof IBackButtonListener
                && ((IBackButtonListener) fragment).onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

}
