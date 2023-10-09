package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.main.CoreQualifiers;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.sync.Result;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.disposables.CompositeDisposable;

class MovieDefaultServices implements MovieServices {

    private final CompositeDisposable compositeDisposable;
    private final Provider<ToggleMovieFavoriteUseCase> toggleMovieFavoriteUseCaseProvider;
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
            Provider<ToggleMovieFavoriteUseCase> toggleMovieFavoriteUseCaseProvider,
            Provider<CheckFavoriteMovieUseCase> checkFavoriteMovieUseCaseProvider,
            Provider<DeleteAllFavoriteMoviesUseCase> deleteAllFavoriteMoviesUseCaseProvider,
            Provider<FilterUpcomingMoviesUseCase> filterUpcomingMoviesUseCaseProvider,
            Provider<FindFavoriteMovieUseCase> findFavoriteMovieUseCaseProvider,
            Provider<FindUpcomingMovieUseCase> findUpcomingMovieUseCaseProvider,
            Provider<ShowFavoriteMoviesUseCase> showFavoriteMoviesUseCaseProvider,
            Provider<ShowUpcomingMoviesUseCase> showUpcomingMoviesUseCaseProvider) {
        this.compositeDisposable = compositeDisposable;
        this.toggleMovieFavoriteUseCaseProvider = toggleMovieFavoriteUseCaseProvider;
        this.checkFavoriteMovieUseCaseProvider = checkFavoriteMovieUseCaseProvider;
        this.deleteAllFavoriteMoviesUseCaseProvider = deleteAllFavoriteMoviesUseCaseProvider;
        this.filterUpcomingMoviesUseCaseProvider = filterUpcomingMoviesUseCaseProvider;
        this.findFavoriteMovieUseCaseProvider = findFavoriteMovieUseCaseProvider;
        this.findUpcomingMovieUseCaseProvider = findUpcomingMovieUseCaseProvider;
        this.showFavoriteMoviesUseCaseProvider = showFavoriteMoviesUseCaseProvider;
        this.showUpcomingMoviesUseCaseProvider = showUpcomingMoviesUseCaseProvider;
    }

    @Override
    public FutureResult<Boolean> toggleFavorite(MovieDetailDS detailDS) {
        ToggleMovieFavoriteUseCase useCase = toggleMovieFavoriteUseCaseProvider.get();
        return useCase.execute(detailDS);
    }

    @Override
    public FutureResult<Boolean> checkFavorite(int movieId) {
        CheckFavoriteMovieUseCase useCase = checkFavoriteMovieUseCaseProvider.get();
        return useCase.execute(movieId);
    }

    @Override
    public FutureResult<Integer> deleteAllFavorite() {
        DeleteAllFavoriteMoviesUseCase useCase = deleteAllFavoriteMoviesUseCaseProvider.get();
        return useCase.execute(null);
    }

    @Override
    public Result<MoviesFilterDS> filterUpcoming(MoviesFilterDS moviesFilterDS) {
        FilterUpcomingMoviesUseCase useCase = filterUpcomingMoviesUseCaseProvider.get();
        return useCase.execute(moviesFilterDS);
    }

    @Override
    public FutureResult<MovieDetailDS> findFavorite(int movieId) {
        FindFavoriteMovieUseCase useCase = findFavoriteMovieUseCaseProvider.get();
        return useCase.execute(movieId);
    }

    @Override
    public FutureResult<MovieDetailDS> findUpcoming(int movieId) {
        FindUpcomingMovieUseCase useCase = findUpcomingMovieUseCaseProvider.get();
        return useCase.execute(movieId);
    }

    @Override
    public FutureResult<MoviesDS> showFavorite() {
        ShowFavoriteMoviesUseCase useCase = showFavoriteMoviesUseCaseProvider.get();
        return useCase.execute(null);
    }

    @Override
    public FutureResult<MoviesDS> showUpcoming(int page) {
        ShowUpcomingMoviesUseCase useCase = showUpcomingMoviesUseCaseProvider.get();
        return useCase.execute(page);
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}