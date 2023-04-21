package com.vanskarner.core.concurrent.rxjava;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface RxFutureFactory {

    <T> FutureResult<T> fromSingle(Single<T> single);

    FutureSimpleResult fromCompletable(Completable completable);

}