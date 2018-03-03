package com.j7arsen.pw.di.app.module;

import com.j7arsen.pw.utils.executor.ThreadExecutor;
import com.j7arsen.pw.domain.executor.IThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arsen on 26.02.2018.
 */
@Module
public class ThreadingModule {

    @Provides
    @Singleton
    public IThreadExecutor provideBackgroundScheduler() {
        return new ThreadExecutor();
    }

}
