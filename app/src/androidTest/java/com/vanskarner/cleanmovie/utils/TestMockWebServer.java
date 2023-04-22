package com.vanskarner.cleanmovie.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class TestMockWebServer {
    private final MockWebServer server;

    public TestMockWebServer(MockWebServer server) {
        this.server = server;
    }

    public void enqueue(int httpCode, String jsonPath) throws IOException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(httpCode);
        mockResponse.setBody(readFile(jsonPath));
        server.enqueue(mockResponse);
    }

    public void enqueueEmpty(int httpCode) {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(httpCode);
        mockResponse.setBody("{}");
        server.enqueue(mockResponse);
    }

    private String readFile(String filePath) throws IOException {
        String receiveString;
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = Objects.requireNonNull(classLoader).getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        while ((receiveString = bufferedReader.readLine()) != null)
            stringBuilder.append(receiveString);
        inputStream.close();
        return stringBuilder.toString();
    }

}