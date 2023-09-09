package com.vanskarner.movie.presentation.upcomingDetail;

import com.vanskarner.movie.businesslogic.services.MovieServices;
import com.vanskarner.movie.presentation.ViewErrorFilter;

import javax.inject.Inject;

class DefaultUpcomingDetailPresenterFactory implements UpcomingDetailPresenterFactory {
    private final UpcomingDetailContract.view view;
    private final MovieServices movieServices;
    private final ViewErrorFilter viewErrorFilter;

    @Inject
    public DefaultUpcomingDetailPresenterFactory(
            UpcomingDetailContract.view view,
            MovieServices movieServices,
            ViewErrorFilter viewErrorFilter) {
        this.view = view;
        this.movieServices = movieServices;
        this.viewErrorFilter = viewErrorFilter;
    }

    @Override
    public UpcomingDetailContract.presenter create() {
        return new UpcomingDetailPresenter(view, movieServices, viewErrorFilter);
    }

}