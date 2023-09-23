package com.vanskarner.movie.businesslogic;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/** @noinspection unused*/
@Module
abstract class MovieErrorModule {

    @Binds
    @IntoMap
    @ClassKey(MovieError.FavoriteLimit.class)
    abstract MovieError provideFavoriteMovieLimit(MovieError.FavoriteLimit movieFavoriteLimit);

}