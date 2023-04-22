package com.vanskarner.cleanmovie.features.movie.upcomingDetail;

import com.vanskarner.cleanmovie.errors.types.ErrorView;
import com.vanskarner.cleanmovie.features.BasePresenter;
import com.vanskarner.cleanmovie.features.movie.MovieDetailModel;

interface UpcomingDetailContract {

    interface view {

        void setReadyViews(boolean readyViews);

        void setMarkedAsFavorite(boolean markedAsFavorite);

        void showUpcomingDetail(MovieDetailModel detailModel);

        void showError(ErrorView<?> errorView);

    }

    interface presenter extends BasePresenter {

        void initialAction(int id);

        void actionFavoriteMovie(MovieDetailModel detailModel);

    }

}