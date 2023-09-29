package com.vanskarner.movie.presentation.favorites;

import com.vanskarner.movie.presentation.MovieBasicDS;
import com.vanskarner.movie.presentation.MovieDetailDS;
import com.vanskarner.movie.presentation.BasePresenter;
import com.vanskarner.movie.presentation.ErrorView;

import java.util.List;

public interface FavoritesContract {

    interface view {

        void showFavorites(List<MovieBasicDS> list);

        void showFavoriteDetail(MovieDetailDS movieDetailDS);

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