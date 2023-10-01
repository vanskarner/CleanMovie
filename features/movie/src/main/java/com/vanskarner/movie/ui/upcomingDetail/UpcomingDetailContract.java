package com.vanskarner.movie.ui.upcomingDetail;

import com.vanskarner.movie.businesslogic.MovieDetailDS;
import com.vanskarner.movie.ui.BasePresenter;
import com.vanskarner.movie.ui.ErrorView;

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