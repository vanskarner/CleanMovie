package com.vanskarner.usecases.movie;

import com.vanskarner.core.main.CoreQualifiers;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.sync.Result;
import com.vanskarner.usecases.movie.ds.MovieDetailDS;
import com.vanskarner.usecases.movie.ds.MoviesDS;
import com.vanskarner.usecases.movie.ds.MoviesFilterDS;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.disposables.CompositeDisposable;

class MovieDefaultServices implements MovieServices {

    private final CompositeDisposable compositeDisposable;
    private final Provider<ActionFavoriteMovieUseCase> actionFavoriteMovieUseCaseProvider;
    private final Provider<CheckFavoriteMovieUseCase> checkFavoriteMovieUseCaseProvider;
    private final Provider<DeleteAllFavoriteMoviesUseCase> deleteAllFavoriteMoviesUseCaseProvider;
    private final Provider<FilterUpcomingMoviesUseCase> filterUpcomingMoviesUseCaseProvider;
    private final Provider<FindFavoriteMovieUseCase> findFavoriteMovieUseCaseProvider;
    private final Provider<FindUpcomingMovieUseCase> findUpcomingMovieUseCaseProvider;
    private final Provider<ShowFavoriteMoviesUseCase> showFavoriteMoviesUseCaseProvider;
    private final Provider<ShowUpcomingMoviesUseCase> showUpcomingMoviesUseCaseProvider;

    @Inject
    public MovieDefaultServices(
            @CoreQualifiers.AsyncCompound CompositeDisposable compositeDisposable,
            Provider<ActionFavoriteMovieUseCase> actionFavoriteMovieUseCaseProvider,
            Provider<CheckFavoriteMovieUseCase> checkFavoriteMovieUseCaseProvider,
            Provider<DeleteAllFavoriteMoviesUseCase> deleteAllFavoriteMoviesUseCaseProvider,
            Provider<FilterUpcomingMoviesUseCase> filterUpcomingMoviesUseCaseProvider,
            Provider<FindFavoriteMovieUseCase> findFavoriteMovieUseCaseProvider,
            Provider<FindUpcomingMovieUseCase> findUpcomingMovieUseCaseProvider,
            Provider<ShowFavoriteMoviesUseCase> showFavoriteMoviesUseCaseProvider,
            Provider<ShowUpcomingMoviesUseCase> showUpcomingMoviesUseCaseProvider) {
        this.compositeDisposable = compositeDisposable;
        this.actionFavoriteMovieUseCaseProvider = actionFavoriteMovieUseCaseProvider;
        this.checkFavoriteMovieUseCaseProvider = checkFavoriteMovieUseCaseProvider;
        this.deleteAllFavoriteMoviesUseCaseProvider = deleteAllFavoriteMoviesUseCaseProvider;
        this.filterUpcomingMoviesUseCaseProvider = filterUpcomingMoviesUseCaseProvider;
        this.findFavoriteMovieUseCaseProvider = findFavoriteMovieUseCaseProvider;
        this.findUpcomingMovieUseCaseProvider = findUpcomingMovieUseCaseProvider;
        this.showFavoriteMoviesUseCaseProvider = showFavoriteMoviesUseCaseProvider;
        this.showUpcomingMoviesUseCaseProvider = showUpcomingMoviesUseCaseProvider;
    }

    @Override
    public FutureResult<Boolean> actionFavorite(MovieDetailDS detailDS) {
        return actionFavoriteMovieUseCaseProvider.get().execute(detailDS);
    }

    @Override
    public FutureResult<Boolean> checkFavorite(int movieId) {
        return checkFavoriteMovieUseCaseProvider.get().execute(movieId);
    }

    @Override
    public FutureResult<Integer> deleteAllFavorite() {
        return deleteAllFavoriteMoviesUseCaseProvider.get().execute();
    }

    @Override
    public Result<MoviesFilterDS> filterUpcoming(MoviesFilterDS moviesFilterDS) {
        return filterUpcomingMoviesUseCaseProvider.get().execute(moviesFilterDS);
    }

    @Override
    public FutureResult<MovieDetailDS> findFavorite(int movieId) {
        return findFavoriteMovieUseCaseProvider.get().execute(movieId);
    }

    @Override
    public FutureResult<MovieDetailDS> findUpcoming(int movieId) {
        return findUpcomingMovieUseCaseProvider.get().execute(movieId);
    }

    @Override
    public FutureResult<MoviesDS> showFavorite() {
        return showFavoriteMoviesUseCaseProvider.get().execute();
    }

    @Override
    public FutureResult<MoviesDS> showUpcoming(int page) {
        return showUpcomingMoviesUseCaseProvider.get().execute(page);
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}