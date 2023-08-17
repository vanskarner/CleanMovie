package com.vanskarner.movie.persistence.remote.utils;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultJsonParserService implements JsonParserService {
    private final Gson gson;

    public DefaultJsonParserService() {
        this.gson = new Gson();
    }

    @Override
    public <T> T fromPath(String jsonPath, Class<T> classOfT) throws Exception {
        Path path = Paths.get(jsonPath);
        byte[] bytes = Files.readAllBytes(path);
        String json = new String(bytes);
        return gson.fromJson(json, classOfT);
    }

}