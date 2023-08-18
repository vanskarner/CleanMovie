package com.vanskarner.movie.persistence.remote.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class DefaultSimulatedServer implements SimulatedServer {
    private final MockWebServer server;

    public DefaultSimulatedServer() {
        this.server = new MockWebServer();
    }

    @Override
    public void start(int port) throws IOException {
        server.start(port);
    }

    @Override
    public void shutdown() throws IOException {
        server.shutdown();
    }

    @Override
    public String url() {
        return server.url("").toString();
    }

    @Override
    public void enqueueFromJsonPath(String jsonPath, int httpCode) throws IOException {
        Path path = Paths.get(jsonPath);
        byte[] bytes = Files.readAllBytes(path);
        String json = new String(bytes);
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(httpCode)
                .setBody(json);
        server.enqueue(mockResponse);
    }

    @Override
    public void enqueueEmpty(int httpCode) {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(httpCode);
        mockResponse.setBody("{}");
        server.enqueue(mockResponse);
    }

}