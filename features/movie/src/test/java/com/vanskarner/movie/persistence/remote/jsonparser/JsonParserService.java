package com.vanskarner.movie.persistence.remote.jsonparser;

public interface JsonParserService {

    <T> T fromPath(String jsonPath, Class<T> classOfT) throws Exception;

}