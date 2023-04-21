package com.vanskarner.core.concurrent;

import com.vanskarner.core.Consumer;

public interface FutureSimpleResult {

    void onResult(Runnable onSuccess, Consumer<? super Throwable> onFailure);

    void await();

    <T> FutureResult<T> toFutureResult(T value);

}