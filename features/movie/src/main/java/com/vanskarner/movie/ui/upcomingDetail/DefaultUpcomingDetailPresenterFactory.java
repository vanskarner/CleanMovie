package com.vanskarner.movie.ui.upcomingDetail;

import com.vanskarner.movie.businesslogic.MovieServices;
import com.vanskarner.movie.ui.ViewErrorFilter;

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