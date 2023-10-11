package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ToggleMovieFavoriteUseCase extends UseCase<FutureResult<Boolean>, MovieDetailDS> {
    private final static int MAXIMUM_MOVIES_SAVED = 2;
    private final CheckFavoriteMovieUseCase checkFavoriteMovieUseCase;
    private final MovieLocalRepository localRepository;
    private final MovieErrorFilter movieErrorFilter;

    @Inject
    public ToggleMovieFavoriteUseCase(
            CheckFavoriteMovieUseCase checkFavoriteMovieUseCase,
            MovieLocalRepository localRepository,
            MovieErrorFilter movieErrorFilter) {
        this.checkFavoriteMovieUseCase = checkFavoriteMovieUseCase;
        this.localRepository = localRepository;
        this.movieErrorFilter = movieErrorFilter;
    }

    @Override
    public FutureResult<Boolean> execute(MovieDetailDS movieDetailDS) {
        return checkFavoriteMovieUseCase.execute(movieDetailDS.basicDS.id)
                .flatMap(exist -> exist ? deleteMovie(movieDetailDS) : saveMovie(movieDetailDS));
    }

    private FutureResult<Boolean> saveMovie(MovieDetailDS movieDetailDS) {
        return localRepository.getNumberMovies()
                .flatMap(numberMovies -> {
                    if (numberMovies >= MAXIMUM_MOVIES_SAVED)
                        throw movieErrorFilter.filter(MovieError.FavoriteLimitError.class);
                    return localRepository.saveMovie(MovieMapper.convert(movieDetailDS))
                            .toFutureResult(true);
                });
    }

    private FutureResult<Boolean> deleteMovie(MovieDetailDS movieDetailDS) {
        return localRepository.deleteMovie(movieDetailDS.basicDS.id).toFutureResult(false);
    }


}