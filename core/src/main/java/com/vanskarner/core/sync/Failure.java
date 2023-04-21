package com.vanskarner.core.sync;

import com.vanskarner.core.Consumer;
import com.vanskarner.core.ExceptionFunction;

import java.util.Objects;

class Failure<T> implements Result<T> {
    private final Exception error;

    public Failure(Exception error) {
        this.error = error;
    }

    @Override
    public <U> Result<U> map(ExceptionFunction<? super T, ? extends U> func) {
        Objects.requireNonNull(func, "func cannot be null");
        return Result.failure(this.error);
    }

    @Override
    public <U> Result<U> flatMap(ExceptionFunction<? super T, ? extends Result<U>> func) {
        Objects.requireNonNull(func, "func cannot be null");
        return Result.failure(this.error);
    }

    @Override
    public T get() throws Exception {
        throw this.error;
    }

    @Override
    public Result<T> onSuccess(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer, "consumer cannot be null");
        return this;
    }

    @Override
    public Result<T> onFailure(Consumer<? super Exception> consumer) {
        Objects.requireNonNull(consumer, "consumer cannot be null");
        consumer.accept(this.error);
        return this;
    }

    @Override
    public void onResult(Consumer<? super T> onSuccess, Consumer<? super Exception> onFailure) {
        Objects.requireNonNull(onSuccess, "onSuccess cannot be null");
        Objects.requireNonNull(onFailure, "onFailure cannot be null");
        onFailure.accept(this.error);
    }

}