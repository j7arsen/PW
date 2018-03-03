package com.j7arsen.pw.domain.interactor.base.flowable;

import com.j7arsen.pw.domain.executor.IThreadExecutor;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by arsen on 25.02.2018.
 */

public abstract class BaseFlowableUseCase<DATA> {

    private CompositeDisposable disposables;
    private IThreadExecutor threadExecutor;

    protected abstract Flowable<DATA> buildUseCase();

    public BaseFlowableUseCase(IThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
    }


    public void execute(final DisposableSubscriber<DATA> disposableSubscriber) {
        final Flowable<DATA> response = getResponse();
        addDisposable(response.subscribeWith(disposableSubscriber));
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable localSubscription) {
        if (localSubscription == null) return;
        disposables.add(localSubscription);
    }

    private Flowable<DATA> getResponse() {
        return buildUseCase()
                .subscribeOn(threadExecutor.getBackgroundThread())
                .observeOn(threadExecutor.getMainThread());
    }
}
