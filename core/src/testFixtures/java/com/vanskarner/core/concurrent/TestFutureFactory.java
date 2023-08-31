package com.vanskarner.core.concurrent;

public final class TestFutureFactory {
    public static <T> FutureResult<T> create(T value) {
        return new TestFutureResult<>(value);
    }

    public static <T> FutureResult<T> create(Exception exception) {
        return new TestFutureResult<>(exception);
    }

}