package com.vanskarner.cleanmovie.ui.movie.favorites;

import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.movie.presentation.favorites.FavoritePresenterModule;
import com.vanskarner.movie.presentation.favorites.FavoritesContract;
import com.vanskarner.singleadapter.SingleAdapter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/** @noinspection unused*/
@Module(includes = FavoritePresenterModule.class)
public abstract class FavoritesModule {

    @Binds
    @PerFragment
    abstract FavoritesContract.view bindView(FavoritesFragment fragment);

/*    @Binds
    @PerFragment
    abstract FavoritesContract.presenter bindPresenter(FavoritesPresenter presenter);*/

    @Provides
    @PerFragment
    @FavoriteQualifiers.FavoriteAdapter
    static SingleAdapter provideSingleAdapter() {
        return new SingleAdapter();
    }

}