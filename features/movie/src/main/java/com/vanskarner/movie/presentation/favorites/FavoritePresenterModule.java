package com.vanskarner.movie.presentation.favorites;

import dagger.Binds;
import dagger.Module;

/**
 * @noinspection unused
 */
@Module
public abstract class FavoritePresenterModule {
    @Binds
    abstract FavoritesPresenterFactory
    bindPresenterFactory(DefaultFavoritesPresenterFactory factory);

}