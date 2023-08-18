package com.vanskarner.movie.persistence.remote.simulatedserver;

public final class SimulatedServerFactory {

    public static SimulatedServer create() {
        return new MockSimulatedServer();
    }

}
