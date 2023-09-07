package com.vanskarner.movie.presentation.upcoming;

import com.vanskarner.movie.businesslogic.ds.MoviesFilterDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;
import com.vanskarner.movie.presentation.MovieModel;
import com.vanskarner.movie.presentation.ViewErrorFilter;

import java.util.List;

import javax.inject.Inject;

class UpcomingPresenter implements UpcomingContract.presenter {

    private final UpcomingContract.view view;
    private final MovieServices movieServices;
    private final List<MovieModel> upcomingList;
    private final List<MovieModel> fullUpcomingList;
    private final ViewErrorFilter viewErrorFilter;

    @Inject
    public UpcomingPresenter(
            UpcomingContract.view view,
            MovieServices movieServices,
            @UpcomingQualifiers.FilterList List<MovieModel> upcomingList,
            @UpcomingQualifiers.FullList List<MovieModel> fullUpcomingList,
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
        if (page == 1) {
            view.setSearchView(false);
            view.setInitialProgress(true);
            movieServices.showUpcoming(page)
                    .map(moviesDS -> MovieViewMapper.convert(moviesDS.list))
                    .onResult(movieModels -> {
                        view.setInitialProgress(false);
                        if (!movieModels.isEmpty()) {
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
                    .map(moviesDS -> MovieViewMapper.convert(moviesDS.list))
                    .onResult(movieModels -> {
                        if (!movieModels.isEmpty())
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
        MoviesFilterDS moviesFilterDS = MovieViewMapper.convert(fullUpcomingList, charSequence);
        movieServices.filterUpcoming(moviesFilterDS)
                .map(moviesFilter -> MovieViewMapper.convert(moviesFilter.filterList))
                .onSuccess(movieModels -> {
                    upcomingList.clear();
                    upcomingList.addAll(movieModels);
                    view.showUpcoming(upcomingList);
                })
                .onFailure(throwable -> view.showError(viewErrorFilter.filter(throwable)));
    }

    @Override
    public void asyncCancel() {
        movieServices.clear();
        view.setPagingProgress(false);
    }

    private void addItems(List<MovieModel> list) {
        fullUpcomingList.addAll(list);
        upcomingList.addAll(list);
        view.showUpcoming(upcomingList);
        view.paginate();
    }

}