package com.vanskarner.core.concurrent;

import com.vanskarner.core.Consumer;
import com.vanskarner.core.ExceptionFunction;

public interface FutureResult<T> {

    <U> FutureResult<U> map(ExceptionFunction<? super T, ? extends U> func);

    <U> FutureResult<U> flatMap(ExceptionFunction<? super T, ? extends FutureResult<U>> func);

    void onResult(Consumer<? super T> onSuccess, Consumer<? super Throwable> onFailure);

    T get();

}