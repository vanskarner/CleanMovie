package com.vanskarner.core.concurrent;

import com.vanskarner.core.Consumer;

/** @noinspection unused*/
class TestFutureSimpleResult implements FutureSimpleResult {
    private Exception error;
    private Runnable runnable;

    public TestFutureSimpleResult(Runnable runnable) {
        this.runnable = runnable;
    }

    public TestFutureSimpleResult(Exception error) {
        this.error = error;
    }

    @Override
    public void onResult(Runnable onSuccess, Consumer<? super Throwable> onFailure) {
        if (error == null) {
            onSuccess.run();
        } else {
            onFailure.accept(error);
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
        return new TestFutureResult<>(value);
    }

}