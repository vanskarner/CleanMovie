package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.movie.presentation.upcomingDetail.UpcomingDetailContract;
import com.vanskarner.movie.presentation.upcomingDetail.UpcomingDetailPresenterModule;

import dagger.Binds;
import dagger.Module;

/** @noinspection unused*/
@Module(includes = UpcomingDetailPresenterModule.class)
public abstract class UpcomingDetailModule {

    @Binds
    @PerFragment
    abstract UpcomingDetailContract.view bindView(UpcomingDetailFragment fragment);

    /*@Binds
    @PerFragment
    abstract UpcomingDetailContract.presenter bindPresenter(UpcomingDetailPresenter presenter);*/

}