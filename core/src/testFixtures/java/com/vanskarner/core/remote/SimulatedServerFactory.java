package com.vanskarner.core.remote;

public final class SimulatedServerFactory {

    public static TestSimulatedServer create(Class<?> testClass) {
        return new MockSimulatedServer(testClass);
    }

}