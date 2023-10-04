package com.vanskarner.cleanmovie.ui.movie.upcoming;

import com.vanskarner.cleanmovie.ui.errors.ErrorView;
import com.vanskarner.cleanmovie.ui.BasePresenter;
import com.vanskarner.cleanmovie.ui.movie.MovieBasicModel;

import java.util.List;

interface UpcomingContract {

    interface view {

        void setSearchView(boolean visible);

        void setInitialProgress(boolean visible);

        void setPagingProgress(boolean visible);

        void enableScroll();

        void paginate();

        void showUpcoming(List<MovieBasicModel> list);

        void showError(ErrorView<?> errorView);

    }

    interface presenter extends BasePresenter {

        void initialLoad(int page);

        void loadMoreItems(int page, boolean unusedSearch);

        void filter(CharSequence charSequence);

    }

}