package com.vanskarner.movie.ui.favorites;

import com.vanskarner.movie.businesslogic.MovieBasicDS;
import com.vanskarner.movie.businesslogic.MovieDetailDS;
import com.vanskarner.movie.ui.BasePresenter;
import com.vanskarner.movie.ui.ErrorView;

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