package com.vanskarner.core.concurrent.rxjava;

import com.vanskarner.core.main.CoreQualifiers;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

class DefaultRxFutureFactory implements RxFutureFactory {
    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler responseThread;

    @Inject
    public DefaultRxFutureFactory(
            @CoreQualifiers.AsyncCompound CompositeDisposable compositeDisposable,
            @CoreQualifiers.ExecutorThread Scheduler executorThread,
            @CoreQualifiers.ResponseThread Scheduler responseThread) {
        this.compositeDisposable = compositeDisposable;
        this.executorThread = executorThread;
        this.responseThread = responseThread;
    }

    @Override
    public <T> FutureResult<T> fromSingle(Single<T> single) {
        return new SingleFutureResult<>(single, compositeDisposable, executorThread,
                responseThread);
    }

    @Override
    public FutureSimpleResult fromCompletable(Completable completable) {
        return new CompletableFutureSimpleResult(completable, compositeDisposable, executorThread,
                responseThread);
    }

}