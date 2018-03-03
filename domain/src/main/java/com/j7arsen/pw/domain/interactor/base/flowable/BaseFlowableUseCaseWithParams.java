package com.j7arsen.pw.domain.interactor.base.flowable;

import com.j7arsen.pw.domain.executor.IThreadExecutor;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by arsen on 25.02.2018.
 */

public abstract class BaseFlowableUseCaseWithParams<DATA, PARAMS> {

    private CompositeDisposable disposables;
    private IThreadExecutor threadExecutor;

    protected abstract Flowable<DATA> buildUseCase(PARAMS params);

    public BaseFlowableUseCaseWithParams(IThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
    }


    public void execute(final PARAMS params, final DisposableSubscriber<DATA> disposableSubscriber) {
        final Flowable<DATA> response = getResponse(params);
        addDisposable(response.subscribeWith(disposableSubscriber));
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable localDisposable) {
        if (localDisposable == null) return;
        disposables.add(localDisposable);
    }

    private Flowable<DATA> getResponse(final PARAMS params) {
        return buildUseCase(params)
                .subscribeOn(threadExecutor.getBackgroundThread())
                .observeOn(threadExecutor.getMainThread());
    }

}

