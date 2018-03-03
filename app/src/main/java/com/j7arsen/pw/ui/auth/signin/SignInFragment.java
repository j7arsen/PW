package com.j7arsen.pw.ui.auth.signin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.j7arsen.pw.ui.main.MainActivity;
import com.j7arsen.pw.R;
import com.j7arsen.pw.app.ValidFields;
import com.j7arsen.pw.base.BaseToolbarFragment;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.utils.IBackButtonListener;
import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.AppUtils;
import com.j7arsen.pw.utils.screencreator.IScreenCreator;
import com.j7arsen.pw.utils.view.EnableButton;
import com.j7arsen.pw.utils.view.progress.ProgressDialogManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by arsen on 26.02.2018.
 */

public class SignInFragment extends BaseToolbarFragment implements ISignInView, IBackButtonListener {

    @BindView(R.id.navigation_bar)
    Toolbar navigationBar;
    @BindView(R.id.et_email)
    AppCompatEditText etEmail;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.et_password)
    AppCompatEditText etPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.btn_sign_in)
    EnableButton btnSignIn;

    @InjectPresenter
    SignInPresenter presenter;

    @Inject
    ResUtils resUtils;

    @Inject
    AppUtils appUtils;

    @Inject
    IScreenCreator screenCreator;

    private Disposable mValidationDisposable;

    public static SignInFragment newInstance() {
        SignInFragment signInFragment = new SignInFragment();
        return signInFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getAuthComponent().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mValidationDisposable = Observable.combineLatest(appUtils.getEmptyWatcherObservable(etEmail), appUtils.getEmptyWatcherObservable(etPassword), (t1, t2) -> (t1 & t2)).subscribe(s -> updateEnablingButton(s));
    }

    private boolean checkEmail() {
        String email = etEmail.getText().toString();
        if (!ValidFields.isValidEmail(email)) {
            tilEmail.setError(resUtils.getString(R.string.email_invalid));
            return false;
        } else {
            tilEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkPassword() {
        String password = etPassword.getText().toString();
        if (!ValidFields.isValidPassword(password)) {
            tilPassword.setError(resUtils.getString(R.string.password_invalid));
            return false;
        } else {
            tilEmail.setErrorEnabled(false);
            return true;
        }
    }

    @OnClick({R.id.btn_sign_in, R.id.tv_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                appUtils.hideKeyboard(mActivity);
                checkEmail();
                checkPassword();
                if(checkEmail() && checkPassword()){
                    presenter.signIn(etEmail.getText().toString(), etPassword.getText().toString());
                }
                break;
            case R.id.tv_sign_up:
                presenter.openSignUpScreen();
                break;
        }
    }

    public void updateEnablingButton(boolean isEnable) {
        btnSignIn.enable(isEnable);
    }

    @Override
    public void successSignIn() {
        mActivity.finish();
        screenCreator.startActivity(mActivity, MainActivity.class);
    }

    @Override
    public void showProgress() {
        ProgressDialogManager.getInstance().startLoading(this);
    }

    @Override
    public void hideProgress() {
        ProgressDialogManager.getInstance().completeLoading();
    }

    @Override
    public void showError(String message) {
        ProgressDialogManager.getInstance().errorLoading(s -> presenter.errorCancel(), message);
    }

    @Override
    public void hideError() {
        ProgressDialogManager.getInstance().unsubscribe();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected void initToolbar() {
        initDefaultToolbar(navigationBar);
        setTitle(resUtils.getString(R.string.sign_in_screen_title));
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    private void unDisposableValidation(){
        if(!mValidationDisposable.isDisposed()){
            mValidationDisposable.dispose();
        }
    }

    @Override
    public void onDestroyView() {
        unDisposableValidation();
        super.onDestroyView();
    }
}
