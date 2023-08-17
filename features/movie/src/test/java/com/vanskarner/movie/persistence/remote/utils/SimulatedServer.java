package com.vanskarner.movie.persistence.remote.utils;

import java.io.IOException;

public interface SimulatedServer {

    void start(int port) throws IOException;

    void shutdown() throws IOException;

    void enqueueFromJsonPath(String jsonPath, int httpCode) throws IOException;

    void enqueueEmpty(int httpCode);

}
