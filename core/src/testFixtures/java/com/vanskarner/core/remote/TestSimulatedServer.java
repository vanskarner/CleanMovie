package com.vanskarner.core.remote;

import java.io.IOException;

public interface TestSimulatedServer {

    void start(int port) throws IOException;

    void shutdown() throws IOException;

    String url();

    void enqueueFrom(String fileName, int httpCode) throws IOException;

    void enqueueEmpty(int httpCode);

}
