package com.vanskarner.core.jsonparser;

public final class TestJsonParserFactory {
    public static TestJsonParser create(Class<?> testClass) {
        return new GsonParser(testClass);
    }
}
