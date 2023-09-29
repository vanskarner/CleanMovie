package com.vanskarner.movie.presentation.upcoming;

import com.vanskarner.movie.presentation.MovieBasicDS;

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
    static List<MovieBasicDS> provideFullList() {
        return new ArrayList<>();
    }

    @Provides
    @UpcomingPresenterQualifiers.FilterList
    static List<MovieBasicDS> provideFilterList() {
        return new ArrayList<>();
    }

}