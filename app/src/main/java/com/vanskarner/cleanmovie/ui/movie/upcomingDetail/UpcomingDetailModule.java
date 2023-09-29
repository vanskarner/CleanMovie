package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.movie.ui.upcomingDetail.UpcomingDetailContract;
import com.vanskarner.movie.ui.upcomingDetail.UpcomingDetailPresenterFactory;
import com.vanskarner.movie.ui.upcomingDetail.UpcomingDetailPresenterModule;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @noinspection unused
 */
@Module(includes = UpcomingDetailPresenterModule.class)
public abstract class UpcomingDetailModule {

    @Binds
    @PerFragment
    abstract UpcomingDetailContract.view bindView(UpcomingDetailFragment fragment);

    @Provides
    @PerFragment
    static UpcomingDetailContract.presenter bindPresenter(UpcomingDetailPresenterFactory factory) {
        return factory.create();
    }

}