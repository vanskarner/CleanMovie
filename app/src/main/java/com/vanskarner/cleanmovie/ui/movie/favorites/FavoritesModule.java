package com.vanskarner.cleanmovie.ui.movie.favorites;

import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.movie.presentation.favorites.FavoritePresenterModule;
import com.vanskarner.movie.presentation.favorites.FavoritesContract;
import com.vanskarner.movie.presentation.favorites.FavoritesPresenterFactory;
import com.vanskarner.singleadapter.SingleAdapter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @noinspection unused
 */
@Module(includes = FavoritePresenterModule.class)
public abstract class FavoritesModule {

    @Binds
    @PerFragment
    public abstract FavoritesContract.view bindView(FavoritesFragment fragment);

    @Provides
    @PerFragment
    public static FavoritesContract.presenter bindPresenter(FavoritesPresenterFactory factory) {
        return factory.create();
    }

    @Provides
    @PerFragment
    @FavoriteQualifiers.FavoriteAdapter
    public static SingleAdapter provideSingleAdapter() {
        return new SingleAdapter();
    }

}