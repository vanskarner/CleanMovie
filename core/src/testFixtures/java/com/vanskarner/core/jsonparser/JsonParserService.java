package com.vanskarner.core.jsonparser;

public interface JsonParserService {

    <T> T from(String fileName, Class<T> classOfT) throws Exception;

}