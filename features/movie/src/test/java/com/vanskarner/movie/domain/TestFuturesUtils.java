package com.vanskarner.movie.domain;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.core.concurrent.rxjava.DefaultRxFutureFactory;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;

import java.util.Optional;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TestFuturesUtils {
    private final Scheduler testScheduler = Schedulers.trampoline();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable,
            testScheduler, testScheduler);

    public TestFuturesUtils() {
    }

    public <T> FutureResult<T> fromData(T data) {
        return rxFutureFactory.fromSingle(Single.just(data));
    }

    public <T> FutureResult<T> fromDataOrElse(T data, Exception exception) {
        Single<T> single = Optional.ofNullable(data)
                .map(Single::just)
                .orElseGet(() -> Single.error(exception));
        return rxFutureFactory.fromSingle(single);
    }

    public FutureSimpleResult completeSuccess() {
        return rxFutureFactory.fromCompletable(Completable.complete());
    }

    public void clear() {
        compositeDisposable.clear();
    }

}