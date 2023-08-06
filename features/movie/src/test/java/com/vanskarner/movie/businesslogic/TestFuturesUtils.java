package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureFactory;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.core.concurrent.rxjava.DefaultFutureFactory;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TestFuturesUtils {
    private final Scheduler testScheduler = Schedulers.trampoline();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final FutureFactory futureFactory = new DefaultFutureFactory(compositeDisposable,
            testScheduler, testScheduler);

    public TestFuturesUtils() {
    }

    public <T> FutureResult<T> fromData(T data) {
        return futureFactory.just(data);
    }

    public <T> FutureResult<T> fromDataOrElse(T data, Exception exception) {
        return futureFactory.fromCallable(() -> {
            if (data != null)
                return data;
            throw exception;
        });
    }

    public FutureSimpleResult completeSuccess() {
        return futureFactory.success();
    }

    public void clear() {
        compositeDisposable.clear();
    }

}