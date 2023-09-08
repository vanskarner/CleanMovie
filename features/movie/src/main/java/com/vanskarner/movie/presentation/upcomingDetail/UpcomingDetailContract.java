package com.vanskarner.movie.presentation.upcomingDetail;

import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.presentation.BasePresenter;
import com.vanskarner.movie.presentation.ErrorView;

public interface UpcomingDetailContract {

    interface view {

        void setReadyViews(boolean readyViews);

        void setMarkedAsFavorite(boolean markedAsFavorite);

        void showUpcomingDetail(MovieDetailDS detailModel);

        void showError(ErrorView<?> errorView);

    }

    interface presenter extends BasePresenter {

        void initialAction(int id);

        void actionFavoriteMovie(MovieDetailDS detailModel);

    }

}