package com.vanskarner.core.concurrent;

/** @noinspection unused*/
public final class TestFutureFactory {

    public static <T> FutureResult<T> createSuccess(T value) {
        return new TestFutureResult<>(value);
    }

    public static <T> FutureResult<T> createFail(Exception exception) {
        return new TestFutureResult<>(exception);
    }

    public static FutureSimpleResult createSimpleSuccess(Runnable exception) {
        return new TestFutureSimpleResult(exception);
    }

    public static FutureSimpleResult createSimpleFail(Exception exception) {
        return new TestFutureSimpleResult(exception);
    }

}