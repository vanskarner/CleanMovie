package com.vanskarner.movie.businesslogic;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

public class MockMovieErrorFilter extends MovieErrorFilter {

    public MockMovieErrorFilter() {
        super(getMapError());
    }

    private static Map<Class<?>, Provider<MovieError>> getMapError() {
        Map<Class<?>, Provider<MovieError>> map = new HashMap<>();
        map.put(MovieError.FavoriteLimitError.class, MovieError.FavoriteLimitError::new);
        return map;
    }

}