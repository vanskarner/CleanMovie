package com.vanskarner.movie.presentation.upcoming;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;
import com.vanskarner.movie.presentation.ViewErrorFilter;

import java.util.List;

import javax.inject.Inject;

class DefaultUpcomingPresenterFactory implements UpcomingPresenterFactory {
    private final UpcomingContract.view view;
    private final MovieServices movieServices;
    private final List<MovieDS> upcomingList;
    private final List<MovieDS> fullUpcomingList;
    private final ViewErrorFilter viewErrorFilter;

    @Inject
    public DefaultUpcomingPresenterFactory(
            UpcomingContract.view view,
            MovieServices movieServices,
            @UpcomingPresenterQualifiers.FilterList List<MovieDS> upcomingList,
            @UpcomingPresenterQualifiers.FullList List<MovieDS> fullUpcomingList,
            ViewErrorFilter viewErrorFilter) {
        this.view = view;
        this.movieServices = movieServices;
        this.upcomingList = upcomingList;
        this.fullUpcomingList = fullUpcomingList;
        this.viewErrorFilter = viewErrorFilter;
    }

    @Override
    public UpcomingContract.presenter create() {
        return new UpcomingPresenter(view,
                movieServices,
                upcomingList,
                fullUpcomingList,
                viewErrorFilter);
    }

}