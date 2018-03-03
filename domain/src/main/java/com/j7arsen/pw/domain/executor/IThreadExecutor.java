package com.j7arsen.pw.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by arsen on 25.02.2018.
 */

public interface IThreadExecutor {

    Scheduler getMainThread();

    Scheduler getBackgroundThread();

}
