package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.presentation.MovieDetailDS;
import com.vanskarner.movie.presentation.MovieError;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ToggleMovieFavoriteUseCase extends BaseAsyncUseCase<MovieDetailDS, Boolean> {
    private final static int MAXIMUM_MOVIES_SAVED = 2;
    private final MovieLocalRepository localRepository;
    private final MovieErrorFilter movieErrorFilter;

    @Inject
    public ToggleMovieFavoriteUseCase(
            MovieLocalRepository localRepository,
            MovieErrorFilter movieErrorFilter) {
        this.localRepository = localRepository;
        this.movieErrorFilter = movieErrorFilter;
    }

    @Override
    public FutureResult<Boolean> execute(MovieDetailDS movieDetailDS) {
        return localRepository.checkMovie(movieDetailDS.movieBasicDS.id)
                .flatMap(exist -> exist ? deleteMovie(movieDetailDS) : saveMovie(movieDetailDS));
    }

    private FutureResult<Boolean> saveMovie(MovieDetailDS movieDetailDS) {
        return localRepository.getNumberMovies()
                .flatMap(numberMovies -> {
                    if (numberMovies >= MAXIMUM_MOVIES_SAVED)
                        throw movieErrorFilter.filter(MovieError.FavoriteLimit.class);
                    return localRepository.saveMovie(MovieMapper.convert(movieDetailDS))
                            .toFutureResult(true);
                });
    }

    private FutureResult<Boolean> deleteMovie(MovieDetailDS movieDetailDS) {
        return localRepository
                .deleteMovie(movieDetailDS.movieBasicDS.id)
                .toFutureResult(false);
    }


}