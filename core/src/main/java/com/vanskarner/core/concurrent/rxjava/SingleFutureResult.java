package com.vanskarner.core.concurrent.rxjava;

import com.vanskarner.core.Consumer;
import com.vanskarner.core.ExceptionFunction;
import com.vanskarner.core.concurrent.FutureResult;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

class SingleFutureResult<T> implements FutureResult<T> {
    private final Single<T> tSingle;
    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler responseThread;

    SingleFutureResult(Single<T> tSingle, CompositeDisposable compositeDisposable,
                       Scheduler executorThread, Scheduler responseThread) {
        this.tSingle = tSingle;
        this.compositeDisposable = compositeDisposable;
        this.executorThread = executorThread;
        this.responseThread = responseThread;
    }

    @Override
    public <U> FutureResult<U> map(ExceptionFunction<? super T, ? extends U> func) {
        return new SingleFutureResult<>(tSingle.map(func::apply), compositeDisposable,
                executorThread, responseThread);
    }

    @Override
    public <U> FutureResult<U> flatMap(
            ExceptionFunction<? super T, ? extends FutureResult<U>> func) {
        Single<U> uSingle = tSingle.flatMap(t -> {
            FutureResult<U> uFutureResult = func.apply(t);
            return ((SingleFutureResult<U>) uFutureResult).tSingle;
        });
        return new SingleFutureResult<>(uSingle, compositeDisposable, executorThread,
                responseThread);
    }

    @Override
    public void onResult(Consumer<? super T> onSuccess, Consumer<? super Throwable> onFailure) {
        Disposable disposable = tSingle
                .subscribeOn(executorThread)
                .observeOn(responseThread)
                .subscribe(onSuccess::accept, onFailure::accept);
        compositeDisposable.add(disposable);
    }

    @Override
    public T get() {
        return tSingle.blockingGet();
    }

}