package com.vanskarner.core.jsonparser;

public final class JsonParserFactory {

    public static JsonParserService create(Class<?> testClass) {
        return new GsonParserService(testClass);
    }

}