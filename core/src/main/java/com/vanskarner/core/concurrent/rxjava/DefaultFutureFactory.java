package com.vanskarner.core.concurrent.rxjava;

import com.vanskarner.core.concurrent.FutureFactory;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.core.main.CoreQualifiers;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class DefaultFutureFactory implements FutureFactory {
    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler responseThread;

    @Inject
    public DefaultFutureFactory(
            @CoreQualifiers.AsyncCompound CompositeDisposable compositeDisposable,
            @CoreQualifiers.ExecutorThread Scheduler executorThread,
            @CoreQualifiers.ResponseThread Scheduler responseThread) {
        this.compositeDisposable = compositeDisposable;
        this.executorThread = executorThread;
        this.responseThread = responseThread;
    }

    @Override
    public <T> FutureResult<T> just(T item) {
        return new SingleFutureResult<>(Single.just(item), compositeDisposable,
                executorThread, responseThread);
    }

    @Override
    public <T> FutureResult<T> fromCallable(Callable<T> single) {
        return new SingleFutureResult<>(Single.fromCallable(single), compositeDisposable,
                executorThread, responseThread);
    }

    @Override
    public FutureSimpleResult fromRunnable(Runnable runnable) {
        return new CompletableFutureSimpleResult(Completable.fromRunnable(runnable),
                compositeDisposable, executorThread, responseThread);
    }

    @Override
    public FutureSimpleResult success() {
        return new CompletableFutureSimpleResult(Completable.complete(),
                compositeDisposable, executorThread, responseThread);
    }

}