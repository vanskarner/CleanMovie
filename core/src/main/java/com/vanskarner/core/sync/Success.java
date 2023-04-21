package com.vanskarner.core.sync;

import com.vanskarner.core.Consumer;
import com.vanskarner.core.ExceptionFunction;

import java.util.Objects;

class Success<T> implements Result<T> {
    private final T value;

    public Success(T value) {
        this.value = value;
    }

    @Override
    public <U> Result<U> map(ExceptionFunction<? super T, ? extends U> func) {
        Objects.requireNonNull(func, "func cannot be null");
        try {
            Objects.requireNonNull(func.apply(this.value),
                    "The map function has returned a null value");
            return Result.success(func.apply(this.value));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    @Override
    public <U> Result<U> flatMap(ExceptionFunction<? super T, ? extends Result<U>> func) {
        Objects.requireNonNull(func, "func cannot be null");
        try {
            Objects.requireNonNull(func.apply(this.value),
                    "The flatmap function has returned a null Result");
            return func.apply(this.value);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public Result<T> onSuccess(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer, "consumer cannot be null");
        consumer.accept(this.value);
        return this;
    }

    @Override
    public Result<T> onFailure(Consumer<? super Exception> consumer) {
        Objects.requireNonNull(consumer, "consumer cannot be null");
        return this;
    }

    @Override
    public void onResult(Consumer<? super T> onSuccess, Consumer<? super Exception> onFailure) {
        Objects.requireNonNull(onSuccess, "onSuccess cannot be null");
        Objects.requireNonNull(onFailure, "onFailure cannot be null");
        onSuccess.accept(this.value);
    }

}