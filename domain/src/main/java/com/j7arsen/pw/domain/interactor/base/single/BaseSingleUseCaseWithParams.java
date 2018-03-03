package com.j7arsen.pw.domain.interactor.base.single;

import com.j7arsen.pw.domain.executor.IThreadExecutor;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by arsen on 25.02.2018.
 */

public abstract class BaseSingleUseCaseWithParams<DATA, PARAMS> {

    private CompositeDisposable disposables;
    private IThreadExecutor threadExecutor;

    protected abstract Single<DATA> buildUseCase(PARAMS params);

    public BaseSingleUseCaseWithParams(IThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
    }


    public void execute(final PARAMS params, final DisposableSingleObserver<DATA> disposableSubscriber) {
        final Single<DATA> response = getResponse(params);
        response.doOnSubscribe(this::addDisposable)
                .subscribeWith(disposableSubscriber);
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

    private Single<DATA> getResponse(final PARAMS params) {
        return buildUseCase(params)
                .subscribeOn(threadExecutor.getBackgroundThread())
                .observeOn(threadExecutor.getMainThread());
    }
}
