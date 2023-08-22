package com.vanskarner.core.jsonparser;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

class GsonParserService implements JsonParserService {
    private final Gson gson;
    private final Class<?> testClass;

    public GsonParserService(Class<?> testClass) {
        this.gson = new Gson();
        this.testClass = testClass;
    }

    @Override
    public <T> T from(String fileName, Class<T> classOfT) throws Exception {
        String json = readFile(fileName);
        return gson.fromJson(json, classOfT);
    }

    private String readFile(String fileName) throws IOException {
        String receiveString;
        InputStream inputStream = testClass.getResourceAsStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(
                Objects.requireNonNull(inputStream));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        while ((receiveString = bufferedReader.readLine()) != null)
            stringBuilder.append(receiveString);
        inputStream.close();
        return stringBuilder.toString();
    }

}