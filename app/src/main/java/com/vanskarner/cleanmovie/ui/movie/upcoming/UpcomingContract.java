package com.vanskarner.cleanmovie.ui.movie.upcoming;

import com.vanskarner.cleanmovie.ui.errors.types.ErrorView;
import com.vanskarner.cleanmovie.ui.BasePresenter;
import com.vanskarner.cleanmovie.ui.movie.MovieModel;

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