package com.vanskarner.movie.businesslogic.services;

import com.vanskarner.movie.businesslogic.error.MovieError;

import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
class MovieErrorFilter {

    private final Map<Class<?>, Provider<MovieError>> mapError;

    @Inject
    public MovieErrorFilter(Map<Class<?>, Provider<MovieError>> mapError) {
        this.mapError = mapError;
    }

    public MovieError filter(Class<? extends MovieError> classKey) {
        return Objects.requireNonNull(mapError.get(classKey)).get();
    }

}