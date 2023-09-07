package com.vanskarner.movie.presentation.favorites;

import com.vanskarner.movie.businesslogic.services.MovieServices;
import com.vanskarner.movie.presentation.ViewErrorFilter;

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
                .onResult(moviesDS -> {
                    view.showFavorites(moviesDS.list);
                    view.setNotFavorites(moviesDS.list.isEmpty());
                }, throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void getFavoriteDetail(int id) {
        movieServices.findFavorite(id)
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