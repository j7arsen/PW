package com.j7arsen.pw.utils;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.j7arsen.pw.app.ValidFields;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by arsen on 28.02.2018.
 */

public class AppUtils {

    public <T> Observable<T> makeObservable(final Callable<T> func) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                try {
                    e.onNext(func.call());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null
                && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView()
                    .getWindowToken(), 0);
        }
    }

    public Observable<Boolean> getEmptyWatcherObservable(@android.support.annotation.NonNull final EditText editText) {
        final PublishSubject<Boolean> subject = PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                subject.onNext(ValidFields.isNotEmptyField(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return subject;
    }

}
