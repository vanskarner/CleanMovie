package com.vanskarner.core.concurrent;

public final class TestFutureSimpleFactory {
    public static FutureSimpleResult create(Runnable runnable) {
        return new TestFutureSimpleResult(runnable);
    }

    public static FutureSimpleResult create(Exception exception) {
        return new TestFutureSimpleResult(exception);
    }

}