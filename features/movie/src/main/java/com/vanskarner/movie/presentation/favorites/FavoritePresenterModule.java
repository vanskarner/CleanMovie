package com.vanskarner.movie.presentation.favorites;

import dagger.Binds;
import dagger.Module;

/** @noinspection unused*/
@Module
public abstract class FavoritePresenterModule {
    @Binds
    abstract FavoritesContract.presenter bindPresenter(FavoritesPresenter presenter);

}