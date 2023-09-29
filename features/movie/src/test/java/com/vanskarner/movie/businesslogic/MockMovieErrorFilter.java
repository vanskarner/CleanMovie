package com.vanskarner.movie.businesslogic;

import com.vanskarner.movie.ui.MovieError;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

public class MockMovieErrorFilter extends MovieErrorFilter {

    public MockMovieErrorFilter() {
        super(getMapError());
    }

    private static Map<Class<?>, Provider<MovieError>> getMapError() {
        Map<Class<?>, Provider<MovieError>> map = new HashMap<>();
        map.put(MovieError.FavoriteLimit.class, MovieError.FavoriteLimit::new);
        return map;
    }

}