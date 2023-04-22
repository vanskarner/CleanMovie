package com.vanskarner.cleanmovie.features.movie.favorites;

import com.vanskarner.cleanmovie.di.scopes.PerFragment;
import com.vanskarner.singleadapter.SingleAdapter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class FavoritesModule {

    @Binds
    @PerFragment
    abstract FavoritesContract.view bindView(FavoritesFragment fragment);

    @Binds
    @PerFragment
    abstract FavoritesContract.presenter bindPresenter(FavoritesPresenter presenter);

    @Provides
    @PerFragment
    @FavoriteQualifiers.FavoriteAdapter
    static SingleAdapter provideSingleAdapter() {
        return new SingleAdapter();
    }

}