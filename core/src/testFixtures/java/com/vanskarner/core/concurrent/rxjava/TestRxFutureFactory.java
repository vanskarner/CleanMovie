package com.vanskarner.core.concurrent.rxjava;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public class TestRxFutureFactory extends DefaultRxFutureFactory {

    public TestRxFutureFactory(CompositeDisposable compositeDisposable,
                               Scheduler executorThread,
                               Scheduler responseThread) {
        super(compositeDisposable, executorThread, responseThread);
    }

}