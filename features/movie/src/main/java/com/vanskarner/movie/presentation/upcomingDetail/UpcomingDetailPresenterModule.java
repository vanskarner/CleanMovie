package com.vanskarner.movie.presentation.upcomingDetail;

import dagger.Binds;
import dagger.Module;

/** @noinspection unused*/
@Module
public abstract class UpcomingDetailPresenterModule {
    @Binds
    abstract UpcomingDetailPresenterFactory
    bindPresenterFactory(DefaultUpcomingDetailPresenterFactory factory);
}