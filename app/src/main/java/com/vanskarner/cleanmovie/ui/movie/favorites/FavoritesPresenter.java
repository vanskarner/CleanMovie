package com.vanskarner.cleanmovie.ui.movie.favorites;

import com.vanskarner.cleanmovie.ui.errors.ViewErrorFilter;
import com.vanskarner.cleanmovie.ui.movie.MovieModelMapper;
import com.vanskarner.movie.businesslogic.MovieServices;

import java.util.Collections;

import javax.inject.Inject;

class FavoritesPresenter implements FavoritesContract.presenter {

    private final FavoritesContract.view view;
    private final MovieServices movieServices;
    private final ViewErrorFilter viewErrorFilter;

    @Inject
    public FavoritesPresenter(
            FavoritesContract.view view,
            MovieServices movieServices,
            ViewErrorFilter viewErrorFilter) {
        this.view = view;
        this.movieServices = movieServices;
        this.viewErrorFilter = viewErrorFilter;
    }

    @Override
    public void getFavorites() {
        movieServices.showFavorite()
                .map(moviesDS -> MovieModelMapper.convert(moviesDS.list))
                .onResult(movieModels -> {
                    view.showFavorites(movieModels);
                    view.setNotFavorites(movieModels.isEmpty());
                }, throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void getFavoriteDetail(int id) {
        movieServices.findFavorite(id)
                .map(MovieModelMapper::convert)
                .onResult(view::showFavoriteDetail,
                        throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void deleteFavorites() {
        movieServices.deleteAllFavorite()
                .onResult(deletedItems -> {
                    view.showFavorites(Collections.emptyList());
                    view.setNotFavorites(true);
                    view.showRemovedItems(deletedItems);
                }, throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void asyncCancel() {
        movieServices.clear();
    }

}