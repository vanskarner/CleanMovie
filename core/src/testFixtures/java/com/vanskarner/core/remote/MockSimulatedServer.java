package com.vanskarner.core.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class MockSimulatedServer implements TestSimulatedServer {
    private final MockWebServer server;
    private final Class<?> testClass;

    public MockSimulatedServer(Class<?> testClass) {
        this.server = new MockWebServer();
        this.testClass = testClass;
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
    public void enqueueFrom(String fileName, int httpCode) throws IOException {
        String json = readFile(fileName);
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

    private String readFile(String fileName) throws IOException {
        String receiveString = "";
        ClassLoader classLoader = testClass.getClassLoader();
        InputStream inputStream = Objects.requireNonNull(classLoader).getResourceAsStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(
                Objects.requireNonNull(inputStream));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        while (receiveString != null) {
            stringBuilder.append(receiveString);
            receiveString = bufferedReader.readLine();
        }
        inputStream.close();
        return stringBuilder.toString();
    }

}