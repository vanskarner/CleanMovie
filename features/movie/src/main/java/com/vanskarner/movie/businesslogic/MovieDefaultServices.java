package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.main.CoreQualifiers;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.sync.Result;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

class MovieDefaultServices implements MovieServices {

    private final CompositeDisposable compositeDisposable;
    private final MovieUseCaseFilter useCaseFilter;

    @Inject
    public MovieDefaultServices(
            @CoreQualifiers.AsyncCompound CompositeDisposable compositeDisposable,
            MovieUseCaseFilter useCaseFilter) {
        this.compositeDisposable = compositeDisposable;
        this.useCaseFilter=useCaseFilter;
    }

    @Override
    public FutureResult<Boolean> toggleFavorite(MovieDetailDS detailDS) {
         return useCaseFilter
                 .filter(ToggleMovieFavoriteUseCase.class)
                 .execute(detailDS);
    }

    @Override
    public FutureResult<Boolean> checkFavorite(int movieId) {
        return useCaseFilter
                .filter(CheckFavoriteMovieUseCase.class)
                .execute(movieId);
    }

    @Override
    public FutureResult<Integer> deleteAllFavorite() {
        return useCaseFilter
                .filter(DeleteAllFavoriteMoviesUseCase.class)
                .execute(null);
    }

    @Override
    public Result<MoviesFilterDS> filterUpcoming(MoviesFilterDS moviesFilterDS) {
         return useCaseFilter
                 .filter(FilterUpcomingMoviesUseCase.class)
                 .execute(moviesFilterDS);
    }

    @Override
    public FutureResult<MovieDetailDS> findFavorite(int movieId) {
         return useCaseFilter
                 .filter(FindFavoriteMovieUseCase.class)
                 .execute(movieId);
    }

    @Override
    public FutureResult<MovieDetailDS> findUpcoming(int movieId) {
         return useCaseFilter
                 .filter(FindUpcomingMovieUseCase.class)
                 .execute(movieId);
    }

    @Override
    public FutureResult<MoviesDS> showFavorite() {
         return useCaseFilter
                 .filter(ShowFavoriteMoviesUseCase.class)
                 .execute(null);
    }

    @Override
    public FutureResult<MoviesDS> showUpcoming(int page) {
         return useCaseFilter
                 .filter(ShowUpcomingMoviesUseCase.class)
                 .execute(page);
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}