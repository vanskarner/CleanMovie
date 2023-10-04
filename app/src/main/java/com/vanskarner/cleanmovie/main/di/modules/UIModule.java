package com.vanskarner.cleanmovie.main.di.modules;

import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.cleanmovie.ui.errors.ViewErrorTypesModule;
import com.vanskarner.cleanmovie.ui.movie.favorites.FavoritesFragment;
import com.vanskarner.cleanmovie.ui.movie.favorites.FavoritesModule;
import com.vanskarner.cleanmovie.ui.movie.upcoming.UpcomingFragment;
import com.vanskarner.cleanmovie.ui.movie.upcoming.UpcomingModule;
import com.vanskarner.cleanmovie.ui.movie.upcomingDetail.UpcomingDetailFragment;
import com.vanskarner.cleanmovie.ui.movie.upcomingDetail.UpcomingDetailModule;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

/** @noinspection unused*/
@Module(includes = {
        AndroidInjectionModule.class,
        ViewErrorTypesModule.class
})
public abstract class UIModule {
    @ContributesAndroidInjector(modules = {FavoritesModule.class})
    @PerFragment
    abstract FavoritesFragment contributeFavoritesFragment();

    @ContributesAndroidInjector(modules = {UpcomingModule.class})
    @PerFragment
    abstract UpcomingFragment contributeUpcomingFragment();

    @ContributesAndroidInjector(modules = {UpcomingDetailModule.class})
    @PerFragment
    abstract UpcomingDetailFragment contributeUpcomingDetailFragment();
}