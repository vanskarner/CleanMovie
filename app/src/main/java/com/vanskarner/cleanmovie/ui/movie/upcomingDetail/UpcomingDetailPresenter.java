package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import com.vanskarner.cleanmovie.ui.errors.ViewErrorFilter;
import com.vanskarner.cleanmovie.ui.movie.MovieDetailModel;
import com.vanskarner.cleanmovie.ui.movie.MovieModelMapper;
import com.vanskarner.movie.businesslogic.MovieDetailDS;
import com.vanskarner.movie.businesslogic.MovieServices;

import javax.inject.Inject;

class UpcomingDetailPresenter implements UpcomingDetailContract.presenter {

    private final UpcomingDetailContract.view view;
    private final MovieServices movieServices;
    private final ViewErrorFilter viewErrorFilter;

    @Inject
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
                .map(MovieModelMapper::convert)
                .onResult(movieDetailModel -> {
                    view.setReadyViews(true);
                    view.showUpcomingDetail(movieDetailModel);
                }, throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void actionFavoriteMovie(MovieDetailModel detailModel) {
        MovieDetailDS item = MovieModelMapper.convert(detailModel);
        movieServices.toggleFavorite(item)
                .onResult(view::setMarkedAsFavorite,
                        throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void asyncCancel() {
        movieServices.clear();
    }

}