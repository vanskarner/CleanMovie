package com.vanskarner.cleanmovie.ui.movie.favorites;

import com.vanskarner.cleanmovie.ui.errors.ErrorView;
import com.vanskarner.cleanmovie.ui.BasePresenter;
import com.vanskarner.cleanmovie.ui.movie.MovieDetailBasicModel;
import com.vanskarner.cleanmovie.ui.movie.MovieBasicModel;

import java.util.List;

interface FavoritesContract {

    interface view {

        void showFavorites(List<MovieBasicModel> list);

        void showFavoriteDetail(MovieDetailBasicModel model);

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