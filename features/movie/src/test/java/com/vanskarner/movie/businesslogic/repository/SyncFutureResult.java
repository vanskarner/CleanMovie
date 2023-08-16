package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.Consumer;
import com.vanskarner.core.ExceptionFunction;
import com.vanskarner.core.concurrent.FutureResult;

public class SyncFutureResult<T> implements FutureResult<T> {
    private T value;
    private Exception error;

    public SyncFutureResult(T value) {
        this.value = value;
    }

    public SyncFutureResult(Exception error) {
        this.error = error;
    }

    @Override
    public <U> FutureResult<U> map(ExceptionFunction<? super T, ? extends U> func) {
        if (error != null)
            return new SyncFutureResult<>(error);
        try {
            U result = func.apply(value);
            return new SyncFutureResult<>(result);
        } catch (Exception e) {
            return new SyncFutureResult<>(e);
        }
    }

    @Override
    public <U> FutureResult<U> flatMap(ExceptionFunction<? super T, ? extends FutureResult<U>> func) {
        if (error != null)
            return new SyncFutureResult<>(error);
        try {
            return func.apply(value);
        } catch (Exception e) {
            return new SyncFutureResult<>(e);
        }
    }

    @Override
    public void onResult(Consumer<? super T> onSuccess, Consumer<? super Throwable> onFailure) {
        if (error != null) {
            onFailure.accept(error);
        } else {
            onSuccess.accept(value);
        }
    }

    @Override
    public T get() throws Exception {
        if (error != null)
            throw error;
        return value;
    }

}