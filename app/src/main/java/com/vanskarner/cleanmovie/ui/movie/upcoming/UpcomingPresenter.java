package com.vanskarner.cleanmovie.ui.movie.upcoming;

import com.vanskarner.cleanmovie.ui.errors.ViewErrorFilter;
import com.vanskarner.cleanmovie.ui.movie.MovieBasicModel;
import com.vanskarner.cleanmovie.ui.movie.MovieViewMapper;
import com.vanskarner.usecases.movie.MovieServices;
import com.vanskarner.usecases.movie.ds.MoviesFilterDS;

import java.util.List;

import javax.inject.Inject;

class UpcomingPresenter implements UpcomingContract.presenter {

    private final UpcomingContract.view view;
    private final MovieServices movieServices;
    private final List<MovieBasicModel> upcomingList;
    private final List<MovieBasicModel> fullUpcomingList;
    private final ViewErrorFilter viewErrorFilter;

    @Inject
    public UpcomingPresenter(
            UpcomingContract.view view,
            MovieServices movieServices,
            @UpcomingQualifiers.FilterList List<MovieBasicModel> upcomingList,
            @UpcomingQualifiers.FullList List<MovieBasicModel> fullUpcomingList,
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
        boolean isPageInitial = page == 1;
        if (isPageInitial) {
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

    private void addItems(List<MovieBasicModel> list) {
        fullUpcomingList.addAll(list);
        upcomingList.addAll(list);
        view.showUpcoming(upcomingList);
        view.paginate();
    }

}