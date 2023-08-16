package com.vanskarner.movie.persistence.remote.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class DefaultSimulatedServer implements SimulatedServer {
    private final MockWebServer server;
    private final Gson gson;

    public DefaultSimulatedServer() {
        this.server = new MockWebServer();
        this.gson = new Gson();
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
    public void enqueue(int httpCode, String jsonPath) throws IOException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(httpCode);
        mockResponse.setBody(readFile(jsonPath));
        server.enqueue(mockResponse);
    }

    @Override
    public void enqueueEmpty(int httpCode) {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(httpCode);
        mockResponse.setBody("{}");
        server.enqueue(mockResponse);
    }

    @Override
    public <T> T fromJson(String jsonPath, Class<T> classOfT) throws IOException {
        return gson.fromJson(readFile(jsonPath), classOfT);
    }

    private String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

}