package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;

import dagger.Binds;
import dagger.Module;

/** @noinspection unused*/
@Module
public abstract class UpcomingDetailModule {

    @Binds
    @PerFragment
    abstract UpcomingDetailContract.view bindView(UpcomingDetailFragment fragment);

    @Binds
    @PerFragment
    abstract UpcomingDetailContract.presenter bindPresenter(UpcomingDetailPresenter presenter);

}