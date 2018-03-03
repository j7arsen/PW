package com.j7arsen.pw.domain.interactor.base.maybe;

import com.j7arsen.pw.domain.executor.IThreadExecutor;

import io.reactivex.Maybe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Created by arsen on 25.02.2018.
 */

public abstract class BaseMaybeUseCaseWithParams<DATA, PARAMS> {

    private CompositeDisposable disposables;
    private IThreadExecutor threadExecutor;

    protected abstract Maybe<DATA> buildUseCase(PARAMS params);

    public BaseMaybeUseCaseWithParams(IThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
    }

    public void execute(final PARAMS params, final DisposableMaybeObserver<DATA> disposableSubscriber) {
        final Maybe<DATA> response = getResponse(params);
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

    private Maybe<DATA> getResponse(final PARAMS params) {
        return buildUseCase(params)
                .subscribeOn(threadExecutor.getBackgroundThread())
                .observeOn(threadExecutor.getMainThread());
    }
}
