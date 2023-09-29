package com.vanskarner.cleanmovie.ui.movie.upcoming;

import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.movie.ui.upcoming.UpcomingContract;
import com.vanskarner.movie.ui.upcoming.UpcomingPresenterFactory;
import com.vanskarner.movie.ui.upcoming.UpcomingPresenterModule;
import com.vanskarner.singleadapter.SingleAdapter;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @noinspection unused
 */
@Module(includes = UpcomingPresenterModule.class)
public abstract class UpcomingModule {

    @Binds
    @PerFragment
    public abstract UpcomingContract.view bindView(UpcomingFragment fragment);

    @Provides
    @PerFragment
    public static UpcomingContract.presenter bindPresenter(UpcomingPresenterFactory factory) {
        return factory.create();
    }

    @Provides
    @PerFragment
    @UpcomingQualifiers.UpcomingAdapter
    public static SingleAdapter provideSingleAdapter() {
        return new SingleAdapter();
    }

    @Provides
    @PerFragment
    public static CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}