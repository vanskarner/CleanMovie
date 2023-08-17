package com.vanskarner.movie.persistence.remote.utils;

public interface JsonParserService {

    <T> T fromPath(String jsonPath, Class<T> classOfT) throws Exception;

}