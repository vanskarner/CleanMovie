package com.vanskarner.core.sync;

import com.vanskarner.core.Consumer;
import com.vanskarner.core.ExceptionFunction;

import java.util.Objects;

public interface Result<T> {

    <U> Result<U> map(ExceptionFunction<? super T, ? extends U> func);

    <U> Result<U> flatMap(ExceptionFunction<? super T, ? extends Result<U>> func);

    T get() throws Exception;

    Result<T> onSuccess(Consumer<? super T> consumer);

    Result<T> onFailure(Consumer<? super Exception> consumer);

    void onResult(Consumer<? super T> onSuccess, Consumer<? super Exception> onFailure);

    static <T> Result<T> failure(Exception error) {
        Objects.requireNonNull(error, "error is null");
        return new Failure<>(error);
    }

    static <T> Result<T> success(T value) {
        Objects.requireNonNull(value, "value is null");
        return new Success<>(value);
    }

}