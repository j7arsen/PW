package com.j7arsen.pw.manager;

import com.j7arsen.pw.app.Constants;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.UserInfoModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arsen on 28.02.2018.
 */
@Singleton
public class DataManager {

    private TokenModel mTokenModel;
    private UserInfoModel mUserInfoModel;

    @Inject
    public DataManager(){}

    public TokenModel getTokenModel() {
        return mTokenModel;
    }

    public void setTokenModel(TokenModel mTokenModel) {
        this.mTokenModel = mTokenModel;
    }

    public UserInfoModel getUserInfoModel() {
        return mUserInfoModel;
    }

    public void setUserInfoModel(UserInfoModel mUserInfoModel) {
        this.mUserInfoModel = mUserInfoModel;
    }

    public Observable getTimerSplash() {
        return Observable.timer(Constants.SPLASH_TIMEOUT, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void logout(){
        mTokenModel = null;
        mUserInfoModel = null;
    }
}
