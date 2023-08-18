package com.vanskarner.movie.persistence.remote.jsonparser;

public final class JsonParserFactory {

    public static JsonParserService create() {
        return new DefaultJsonParserService();
    }

}