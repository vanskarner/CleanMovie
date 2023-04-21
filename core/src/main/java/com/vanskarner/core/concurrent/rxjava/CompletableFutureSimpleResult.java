package com.vanskarner.core.concurrent.rxjava;

import com.vanskarner.core.Consumer;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

class CompletableFutureSimpleResult implements FutureSimpleResult {
    private final Completable completable;
    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler responseThread;

    CompletableFutureSimpleResult(Completable completable, CompositeDisposable compositeDisposable,
                                  Scheduler executorThread, Scheduler responseThread) {
        this.completable = completable;
        this.compositeDisposable = compositeDisposable;
        this.executorThread = executorThread;
        this.responseThread = responseThread;
    }

    @Override
    public void onResult(Runnable onSuccess, Consumer<? super Throwable> onFailure) {
        Disposable disposable = completable.subscribeOn(executorThread)
                .observeOn(responseThread)
                .subscribe(onSuccess::run, onFailure::accept);
        compositeDisposable.add(disposable);
    }

    @Override
    public void await() {
        completable.blockingAwait();
    }

    @Override
    public <T> FutureResult<T> toFutureResult(T value) {
        Single<T> single = completable.toSingleDefault(value);
        return new SingleFutureResult<>(single, compositeDisposable, executorThread,
                responseThread);
    }

}