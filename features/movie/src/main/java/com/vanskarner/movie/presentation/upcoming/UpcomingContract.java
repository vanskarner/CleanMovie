package com.vanskarner.movie.presentation.upcoming;

import com.vanskarner.movie.presentation.BasePresenter;
import com.vanskarner.movie.presentation.ErrorView;
import com.vanskarner.movie.presentation.MovieModel;

import java.util.List;

interface UpcomingContract {

    interface view {

        void setSearchView(boolean visible);

        void setInitialProgress(boolean visible);

        void setPagingProgress(boolean visible);

        void enableScroll();

        void paginate();

        void showUpcoming(List<MovieModel> list);

        void showError(ErrorView<?> errorView);

    }

    interface presenter extends BasePresenter {

        void initialLoad(int page);

        void loadMoreItems(int page, boolean unusedSearch);

        void filter(CharSequence charSequence);

    }

}