package com.vanskarner.movie.ui.upcoming;

import com.vanskarner.movie.ui.MovieBasicDS;
import com.vanskarner.movie.ui.MoviesDS;
import com.vanskarner.movie.ui.MoviesFilterDS;
import com.vanskarner.movie.businesslogic.MovieServices;
import com.vanskarner.movie.ui.ViewErrorFilter;

import java.util.List;

class UpcomingPresenter implements UpcomingContract.presenter {
    private final UpcomingContract.view view;
    private final MovieServices movieServices;
    private final List<MovieBasicDS> upcomingList;
    private final List<MovieBasicDS> fullUpcomingList;
    private final ViewErrorFilter viewErrorFilter;

    public UpcomingPresenter(
            UpcomingContract.view view,
            MovieServices movieServices,
            List<MovieBasicDS> upcomingList, List<MovieBasicDS> fullUpcomingList,
            ViewErrorFilter viewErrorFilter) {
        this.view = view;
        this.movieServices = movieServices;
        this.upcomingList = upcomingList;
        this.fullUpcomingList = fullUpcomingList;
        this.viewErrorFilter = viewErrorFilter;
    }

    @Override
    public void initialLoad(int page) {
        view.enableScroll();
        boolean isInitialPage = page == 1;
        if (isInitialPage) {
            view.setSearchView(false);
            view.setInitialProgress(true);
            movieServices.showUpcoming(page)
                    .onResult(movieModels -> {
                        view.setInitialProgress(false);
                        if (!movieModels.list.isEmpty()) {
                            addItems(movieModels);
                            view.setSearchView(true);
                        }
                    }, throwable -> {
                        view.setInitialProgress(false);
                        view.showError(viewErrorFilter.filter(throwable));
                    });
        }
    }

    @Override
    public void loadMoreItems(int page, boolean unusedSearch) {
        if (unusedSearch) {
            view.setPagingProgress(true);
            movieServices.showUpcoming(page)
                    .onResult(movieModels -> {
                        if (!movieModels.list.isEmpty())
                            addItems(movieModels);
                    }, throwable -> {
                        view.enableScroll();
                        view.setPagingProgress(false);
                        view.showError(viewErrorFilter.filter(throwable));
                    });
        }
    }

    @Override
    public void filter(CharSequence charSequence) {
        MoviesFilterDS moviesFilterDS = new MoviesFilterDS(fullUpcomingList, charSequence);
        movieServices.filterUpcoming(moviesFilterDS)
                .onSuccess(movieModels -> {
                    upcomingList.clear();
                    upcomingList.addAll(movieModels.filterList);
                    view.showUpcoming(upcomingList);
                })
                .onFailure(throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void asyncCancel() {
        movieServices.clear();
        view.setPagingProgress(false);
    }

    private void addItems(MoviesDS moviesDS) {
        fullUpcomingList.addAll(moviesDS.list);
        upcomingList.addAll(moviesDS.list);
        view.showUpcoming(upcomingList);
        view.paginate();
    }

}