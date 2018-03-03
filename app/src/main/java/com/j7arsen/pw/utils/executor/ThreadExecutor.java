package com.j7arsen.pw.utils.executor;

import com.j7arsen.pw.domain.executor.IThreadExecutor;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arsen on 25.02.2018.
 */

public class ThreadExecutor implements IThreadExecutor {

    @Override
    public Scheduler getMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler getBackgroundThread() {
        return Schedulers.io();
    }

}
