package com.j7arsen.pw.ui.auth.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.j7arsen.pw.R;
import com.j7arsen.pw.app.ValidFields;
import com.j7arsen.pw.base.BaseToolbarFragment;
import com.j7arsen.pw.di.ComponentManager;
import com.j7arsen.pw.di.app.qualifier.Global;
import com.j7arsen.pw.utils.AppUtils;
import com.j7arsen.pw.utils.IBackButtonListener;
import com.j7arsen.pw.utils.ResUtils;
import com.j7arsen.pw.utils.Screens;
import com.j7arsen.pw.utils.view.EnableButton;
import com.j7arsen.pw.utils.view.progress.ProgressDialogManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import ru.terrakok.cicerone.Router;

/**
 * Created by arsen on 26.02.2018.
 */

public class SignUpFragment extends BaseToolbarFragment implements ISignUpView, IBackButtonListener {

    @BindView(R.id.navigation_bar)
    Toolbar navigationBar;
    @BindView(R.id.et_name)
    AppCompatEditText etName;
    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.et_email)
    AppCompatEditText etEmail;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.et_password)
    AppCompatEditText etPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.et_confirm_password)
    AppCompatEditText etConfirmPassword;
    @BindView(R.id.til_confirm_password)
    TextInputLayout tilConfirmPassword;
    @BindView(R.id.btn_sign_up)
    EnableButton btnSignUp;

    @InjectPresenter
    SignUpPresenter presenter;

    @Inject
    ResUtils resUtils;

    @Inject
    AppUtils appUtils;

    @Inject
    @Global
    Router router;

    private Disposable mValidationDisposable;

    @ProvidePresenter
    public SignUpPresenter createSignUpPresenter() {
        return new SignUpPresenter(router);
    }

    public static SignUpFragment newInstance() {
        SignUpFragment signUpFragment = new SignUpFragment();
        return signUpFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getAuthComponent().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mValidationDisposable = Observable.combineLatest(appUtils.getEmptyWatcherObservable(etName), appUtils.getEmptyWatcherObservable(etEmail), appUtils.getEmptyWatcherObservable(etPassword), appUtils.getEmptyWatcherObservable(etConfirmPassword), (t1, t2, t3, t4) -> (t1 & t2 & t3 & t4)).subscribe(s -> updateEnablingButton(s));
    }

    @OnClick(R.id.btn_sign_up)
    public void onViewClicked() {
        appUtils.hideKeyboard(mActivity);
        checkPassword();
        checkEmail();
        checkEqualsPassword();
        if(checkPassword() && checkEmail() && checkEqualsPassword()){
            presenter.signUp(etName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
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

    private boolean checkEqualsPassword() {
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        if (password.equals(confirmPassword)) {
            tilConfirmPassword.setErrorEnabled(false);
            return true;
        } else {
            tilConfirmPassword.setError(resUtils.getString(R.string.password_confirm_invalid));
            return false;
        }
    }

    public void updateEnablingButton(boolean isEnable) {
        btnSignUp.enable(isEnable);
    }

    @Override
    public void successSignUp() {
        router.exitWithResult(Screens.SIGN_UP_RESULT, null);
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
        return R.layout.fragment_sign_up;
    }

    @Override
    protected void initToolbar() {
        initDefaultBackToolbar(navigationBar, () -> presenter.onBackPressed());
        setTitle(resUtils.getString(R.string.sign_up_screen_title));
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
