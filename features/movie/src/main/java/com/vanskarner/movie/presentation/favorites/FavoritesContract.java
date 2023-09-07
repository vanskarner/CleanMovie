package com.vanskarner.movie.presentation.favorites;

import com.vanskarner.movie.presentation.BasePresenter;
import com.vanskarner.movie.presentation.ErrorView;
import com.vanskarner.movie.presentation.MovieDetailModel;
import com.vanskarner.movie.presentation.MovieModel;

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