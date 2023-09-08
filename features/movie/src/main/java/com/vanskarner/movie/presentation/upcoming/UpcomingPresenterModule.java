package com.vanskarner.movie.presentation.upcoming;

import com.vanskarner.movie.businesslogic.ds.MovieDS;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/** @noinspection unused*/
@Module
public abstract class UpcomingPresenterModule {
    @Binds
    abstract UpcomingContract.presenter bindPresenter(UpcomingPresenter favoritesPresenter);

    @Provides
    @UpcomingQualifiers.FullList
    static List<MovieDS> provideFullList() {
        return new ArrayList<>();
    }

    @Provides
    @UpcomingQualifiers.FilterList
    static List<MovieDS> provideFilterList() {
        return new ArrayList<>();
    }

}