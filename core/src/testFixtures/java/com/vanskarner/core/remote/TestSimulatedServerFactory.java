package com.vanskarner.core.remote;

public final class TestSimulatedServerFactory {

    public static TestSimulatedServer create(Class<?> testClass) {
        return new MockSimulatedServer(testClass);
    }

}