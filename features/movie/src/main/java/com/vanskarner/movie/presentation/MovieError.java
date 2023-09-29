package com.vanskarner.movie.presentation;

import javax.inject.Inject;
import javax.inject.Singleton;

public abstract class MovieError extends RuntimeException {

    @Singleton
    public static class FavoriteLimit extends MovieError {
        @Inject
        public FavoriteLimit() {
        }
    }

}