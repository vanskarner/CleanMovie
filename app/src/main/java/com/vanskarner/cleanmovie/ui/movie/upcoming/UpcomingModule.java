package com.vanskarner.cleanmovie.ui.movie.upcoming;

import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.movie.presentation.upcoming.UpcomingContract;
import com.vanskarner.movie.presentation.upcoming.UpcomingPresenterFactory;
import com.vanskarner.movie.presentation.upcoming.UpcomingPresenterModule;
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
    abstract UpcomingContract.view bindView(UpcomingFragment fragment);

    @Provides
    @PerFragment
    static UpcomingContract.presenter bindPresenter(UpcomingPresenterFactory factory) {
        return factory.create();
    }

    @Provides
    @PerFragment
    @UpcomingQualifiers.UpcomingAdapter
    static SingleAdapter provideSingleAdapter() {
        return new SingleAdapter();
    }

    @Provides
    @PerFragment
    static CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}