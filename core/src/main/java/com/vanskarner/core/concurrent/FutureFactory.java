package com.vanskarner.core.concurrent;

import java.util.concurrent.Callable;

public interface FutureFactory {
    <T> FutureResult<T> just(T item);

    <T> FutureResult<T> fromCallable(Callable<T> single);

    FutureSimpleResult fromRunnable(Runnable runnable);

    FutureSimpleResult success();

}