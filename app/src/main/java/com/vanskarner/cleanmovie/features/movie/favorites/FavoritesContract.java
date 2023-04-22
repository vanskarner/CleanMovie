package com.vanskarner.cleanmovie.features.movie.favorites;

import com.vanskarner.cleanmovie.errors.types.ErrorView;
import com.vanskarner.cleanmovie.features.BasePresenter;
import com.vanskarner.cleanmovie.features.movie.MovieDetailModel;
import com.vanskarner.cleanmovie.features.movie.MovieModel;

import java.util.List;

interface FavoritesContract {

    interface view {

        void showFavorites(List<MovieModel> list);

        void showFavoriteDetail(MovieDetailModel model);

        void setNotFavorites(boolean visible);

        void showError(ErrorView<?> errorView);

        void showRemovedItems(int quantity);

    }

    interface presenter extends BasePresenter {

        void getFavorites();

        void getFavoriteDetail(int id);

        void deleteFavorites();

    }

}