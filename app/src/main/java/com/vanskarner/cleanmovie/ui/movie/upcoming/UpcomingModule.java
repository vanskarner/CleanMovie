package com.vanskarner.cleanmovie.ui.movie.upcoming;

import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.cleanmovie.ui.movie.MovieModel;
import com.vanskarner.singleadapter.SingleAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class UpcomingModule {

    @Binds
    @PerFragment
    abstract UpcomingContract.view bindView(UpcomingFragment fragment);

    @Binds
    @PerFragment
    abstract UpcomingContract.presenter bindPresenter(UpcomingPresenter presenter);

    @Provides
    @PerFragment
    @UpcomingQualifiers.FullList
    static List<MovieModel> provideFullList() {
        return new ArrayList<>();
    }

    @Provides
    @PerFragment
    @UpcomingQualifiers.FilterList
    static List<MovieModel> provideFilterList() {
        return new ArrayList<>();
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