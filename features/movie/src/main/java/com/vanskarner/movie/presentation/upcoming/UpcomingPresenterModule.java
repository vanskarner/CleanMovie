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
    abstract UpcomingPresenterFactory
    bindPresenterFactory(DefaultUpcomingPresenterFactory factory);

    @Provides
    @UpcomingPresenterQualifiers.FullList
    static List<MovieDS> provideFullList() {
        return new ArrayList<>();
    }

    @Provides
    @UpcomingPresenterQualifiers.FilterList
    static List<MovieDS> provideFilterList() {
        return new ArrayList<>();
    }

}