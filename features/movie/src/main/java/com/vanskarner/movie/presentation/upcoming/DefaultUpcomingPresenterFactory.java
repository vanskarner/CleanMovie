package com.vanskarner.movie.presentation.upcoming;

import com.vanskarner.movie.businesslogic.ds.MovieBasicDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;
import com.vanskarner.movie.presentation.ViewErrorFilter;

import java.util.List;

import javax.inject.Inject;

class DefaultUpcomingPresenterFactory implements UpcomingPresenterFactory {
    private final UpcomingContract.view view;
    private final MovieServices movieServices;
    private final List<MovieBasicDS> upcomingList;
    private final List<MovieBasicDS> fullUpcomingList;
    private final ViewErrorFilter viewErrorFilter;

    @Inject
    public DefaultUpcomingPresenterFactory(
            UpcomingContract.view view,
            MovieServices movieServices,
            @UpcomingPresenterQualifiers.FilterList List<MovieBasicDS> upcomingList,
            @UpcomingPresenterQualifiers.FullList List<MovieBasicDS> fullUpcomingList,
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