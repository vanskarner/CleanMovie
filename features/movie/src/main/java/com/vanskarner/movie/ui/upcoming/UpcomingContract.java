package com.vanskarner.movie.ui.upcoming;

import com.vanskarner.movie.ui.MovieBasicDS;
import com.vanskarner.movie.ui.BasePresenter;
import com.vanskarner.movie.ui.ErrorView;

import java.util.List;

public interface UpcomingContract {

    interface view {

        void setSearchView(boolean visible);

        void setInitialProgress(boolean visible);

        void setPagingProgress(boolean visible);

        void enableScroll();

        void paginate();

        void showUpcoming(List<MovieBasicDS> list);

        void showError(ErrorView<?> errorView);

    }

    interface presenter extends BasePresenter {

        void initialLoad(int page);

        void loadMoreItems(int page, boolean unusedSearch);

        void filter(CharSequence charSequence);

    }

}