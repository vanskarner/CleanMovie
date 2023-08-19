package com.vanskarner.core.concurrent;

import com.vanskarner.core.Consumer;
import com.vanskarner.core.ExceptionFunction;

public class TestFutureResult<T> implements FutureResult<T> {
    private T value;
    private Exception error;

    public TestFutureResult(T value) {
        this.value = value;
    }

    public TestFutureResult(Exception error) {
        this.error = error;
    }

    @Override
    public <U> FutureResult<U> map(ExceptionFunction<? super T, ? extends U> func) {
        if (error != null)
            return new TestFutureResult<>(error);
        try {
            U result = func.apply(value);
            return new TestFutureResult<>(result);
        } catch (Exception e) {
            return new TestFutureResult<>(e);
        }
    }

    @Override
    public <U> FutureResult<U> flatMap(ExceptionFunction<? super T, ? extends FutureResult<U>> func) {
        if (error != null)
            return new TestFutureResult<>(error);
        try {
            return func.apply(value);
        } catch (Exception e) {
            return new TestFutureResult<>(e);
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