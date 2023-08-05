package com.vanskarner.movie.businesslogic.services;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.businesslogic.bases.BaseAsyncUseCase;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.error.MovieError;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ActionFavoriteMovieUseCase extends BaseAsyncUseCase<MovieDetailDS, Boolean> {
    private final static int MAXIMUM_MOVIES_SAVED = 2;
    private final MovieLocalRepository localRepository;
    private final MovieErrorFilter movieErrorFilter;

    @Inject
    public ActionFavoriteMovieUseCase(
            MovieLocalRepository localRepository,
            MovieErrorFilter movieErrorFilter) {
        this.localRepository = localRepository;
        this.movieErrorFilter = movieErrorFilter;
    }

    @Override
    public FutureResult<Boolean> execute(MovieDetailDS movieDetailDS) {
        FutureResult<Boolean> deleteItem = localRepository.deleteMovie(movieDetailDS.id)
                .toFutureResult(false);
        FutureResult<Boolean> saveItem = localRepository.getNumberMovies()
                .flatMap(integer -> {
                    if (integer >= MAXIMUM_MOVIES_SAVED)
                        throw movieErrorFilter.filter(MovieError.FavoriteLimit.class);
                    return localRepository.saveMovie(MovieMapper.convert(movieDetailDS))
                            .toFutureResult(true);
                });
        return localRepository.checkMovie(movieDetailDS.id)
                .flatMap(exists -> exists ? deleteItem : saveItem);
    }

}