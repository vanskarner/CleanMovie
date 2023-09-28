package com.vanskarner.domain.movie.service;

import javax.inject.Inject;
import javax.inject.Singleton;

public abstract class MovieError extends RuntimeException {
    @Singleton
    public static class MovieFavoriteLimit extends MovieError {
        @Inject
        public MovieFavoriteLimit() {
        }
    }
}