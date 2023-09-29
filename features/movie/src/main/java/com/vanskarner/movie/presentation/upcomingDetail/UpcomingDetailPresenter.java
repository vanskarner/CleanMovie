package com.vanskarner.movie.presentation.upcomingDetail;

import com.vanskarner.movie.MovieDetailDS;
import com.vanskarner.movie.MovieServices;
import com.vanskarner.movie.presentation.ViewErrorFilter;

class UpcomingDetailPresenter implements UpcomingDetailContract.presenter {

    private final UpcomingDetailContract.view view;
    private final MovieServices movieServices;
    private final ViewErrorFilter viewErrorFilter;

    public UpcomingDetailPresenter(
            UpcomingDetailContract.view view,
            MovieServices movieServices,
            ViewErrorFilter viewErrorFilter) {
        this.view = view;
        this.movieServices = movieServices;
        this.viewErrorFilter = viewErrorFilter;
    }

    @Override
    public void initialAction(int id) {
        view.setReadyViews(false);
        movieServices.checkFavorite(id)
                .onResult(view::setMarkedAsFavorite,
                        throwable -> view.showError(viewErrorFilter.filter(throwable)));
        movieServices.findUpcoming(id)
                .onResult(movieDetailModel -> {
                    view.setReadyViews(true);
                    view.showUpcomingDetail(movieDetailModel);
                }, throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void actionFavoriteMovie(MovieDetailDS detailModel) {
        movieServices.toggleFavorite(detailModel)
                .onResult(view::setMarkedAsFavorite,
                        throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void asyncCancel() {
        movieServices.clear();
    }

}