package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import com.vanskarner.cleanmovie.ui.errors.types.ErrorView;
import com.vanskarner.cleanmovie.ui.BasePresenter;
import com.vanskarner.cleanmovie.ui.movie.MovieDetailModel;

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