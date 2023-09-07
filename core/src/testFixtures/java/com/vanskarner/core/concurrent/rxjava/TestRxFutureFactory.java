package com.vanskarner.core.concurrent.rxjava;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public final class TestRxFutureFactory {

    public static RxFutureFactory create(CompositeDisposable compositeDisposable,
                                         Scheduler executorThread,
                                         Scheduler responseThread) {
        return new DefaultRxFutureFactory(compositeDisposable, executorThread, responseThread);
    }

}