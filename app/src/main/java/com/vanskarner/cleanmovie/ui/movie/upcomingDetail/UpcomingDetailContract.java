package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import com.vanskarner.cleanmovie.ui.errors.ErrorView;
import com.vanskarner.cleanmovie.ui.BasePresenter;
import com.vanskarner.cleanmovie.ui.movie.MovieDetailBasicModel;

interface UpcomingDetailContract {

    interface view {

        void setReadyViews(boolean readyViews);

        void setMarkedAsFavorite(boolean markedAsFavorite);

        void showUpcomingDetail(MovieDetailBasicModel detailModel);

        void showError(ErrorView<?> errorView);

    }

    interface presenter extends BasePresenter {

        void initialAction(int id);

        void actionFavoriteMovie(MovieDetailBasicModel detailModel);

    }

}