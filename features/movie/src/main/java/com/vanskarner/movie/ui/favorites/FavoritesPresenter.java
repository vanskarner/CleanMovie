package com.vanskarner.movie.ui.favorites;

import com.vanskarner.movie.businesslogic.MovieServices;
import com.vanskarner.movie.ui.ViewErrorFilter;

import java.util.Collections;

class FavoritesPresenter implements FavoritesContract.presenter {
    private final FavoritesContract.view view;
    private final MovieServices movieServices;
    private final ViewErrorFilter viewErrorFilter;

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