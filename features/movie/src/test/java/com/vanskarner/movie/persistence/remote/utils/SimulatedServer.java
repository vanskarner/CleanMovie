package com.vanskarner.movie.persistence.remote.utils;

import java.io.IOException;

public interface SimulatedServer {

    void start(int port) throws IOException;

    void shutdown() throws IOException;

    void enqueue(int httpCode, String jsonPath) throws IOException;

    void enqueueEmpty(int httpCode);

    <T> T fromJson(String jsonPath, Class<T> classOfT) throws IOException;

}
