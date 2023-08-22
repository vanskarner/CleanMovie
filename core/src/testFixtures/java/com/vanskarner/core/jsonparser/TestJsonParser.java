package com.vanskarner.core.jsonparser;

public interface TestJsonParser {

    <T> T from(String fileName, Class<T> classOfT) throws Exception;

}