package com.vanskarner.core;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;

/** @noinspection unused*/
public class SyncFutureSimpleResult implements FutureSimpleResult {
    private Exception error;
    private Runnable runnable;

    public SyncFutureSimpleResult(Runnable runnable) {
        this.runnable = runnable;
    }

    public SyncFutureSimpleResult(Exception error) {
        this.error = error;
    }

    @Override
    public void onResult(Runnable onSuccess, Consumer<? super Throwable> onFailure) {
        if (error != null) {
            onFailure.accept(error);
        } else {
            onSuccess.run();
        }
    }

    @Override
    public void await() throws Exception {
        if (error != null)
            throw error;
        runnable.run();
    }

    @Override
    public <T> FutureResult<T> toFutureResult(T value) {
        runnable.run();
        return new SyncFutureResult<>(value);
    }

}