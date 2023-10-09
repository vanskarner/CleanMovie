package com.vanskarner.movie.businesslogic;

import javax.inject.Inject;
import javax.inject.Singleton;

public abstract class MovieError extends RuntimeException {

    @Singleton
    public static class FavoriteLimitError extends MovieError {
        @Inject
        public FavoriteLimitError() {
        }
    }

}