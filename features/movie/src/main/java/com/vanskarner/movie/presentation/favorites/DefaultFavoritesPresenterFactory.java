package com.vanskarner.movie.presentation.favorites;

import com.vanskarner.movie.businesslogic.MovieServices;
import com.vanskarner.movie.presentation.ViewErrorFilter;

import javax.inject.Inject;
class DefaultFavoritesPresenterFactory implements FavoritesPresenterFactory {
    private final FavoritesContract.view view;
    private final MovieServices movieServices;
    private final ViewErrorFilter viewErrorFilter;

    @Inject
    public DefaultFavoritesPresenterFactory(
            FavoritesContract.view view,
            MovieServices movieServices,
            ViewErrorFilter viewErrorFilter) {
        this.view = view;
        this.movieServices = movieServices;
        this.viewErrorFilter = viewErrorFilter;
    }

    @Override
    public FavoritesContract.presenter create() {
        return new FavoritesPresenter(view, movieServices, viewErrorFilter);
    }

}