package com.vanskarner.movie.domain.services;

import com.vanskarner.movie.domain.error.MovieError;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
abstract class MovieErrorModule {

    @Binds
    @IntoMap
    @ClassKey(MovieError.FavoriteLimit.class)
    abstract MovieError provideFavoriteMovieLimit(MovieError.FavoriteLimit movieFavoriteLimit);

}