package com.vanskarner.cleanmovie.features.movie.upcomingDetail;

import com.vanskarner.cleanmovie.di.scopes.PerFragment;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class UpcomingDetailModule {

    @Binds
    @PerFragment
    abstract UpcomingDetailContract.view bindView(UpcomingDetailFragment fragment);

    @Binds
    @PerFragment
    abstract UpcomingDetailContract.presenter bindPresenter(UpcomingDetailPresenter presenter);

}