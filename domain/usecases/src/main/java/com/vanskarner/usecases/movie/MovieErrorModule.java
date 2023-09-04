package com.vanskarner.usecases.movie;

import com.vanskarner.usecases.DomainError;
import com.vanskarner.usecases.movie.error.MovieFavoriteLimit;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/** @noinspection unused*/
@Module
abstract class MovieErrorModule {

    @Binds
    @IntoMap
    @ClassKey(MovieFavoriteLimit.class)
    abstract DomainError provideFavoriteMovieLimit(MovieFavoriteLimit movieFavoriteLimit);

}