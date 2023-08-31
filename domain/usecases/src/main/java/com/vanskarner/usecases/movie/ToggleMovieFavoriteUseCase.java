package com.vanskarner.usecases.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.usecases.DomainErrorFilter;
import com.vanskarner.usecases.bases.BaseAsyncUseCase;
import com.vanskarner.usecases.movie.ds.MovieDetailDS;
import com.vanskarner.usecases.movie.error.MovieFavoriteLimit;
import com.vanskarner.usecases.movie.repository.MovieLocalRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ToggleMovieFavoriteUseCase extends BaseAsyncUseCase<MovieDetailDS, Boolean> {
    private final static int MAXIMUM_MOVIES_SAVED = 2;
    private final MovieLocalRepository localRepository;
    private final DomainErrorFilter domainErrorFilter;

    @Inject
    public ToggleMovieFavoriteUseCase(
            MovieLocalRepository localRepository,
            DomainErrorFilter domainErrorFilter) {
        this.localRepository = localRepository;
        this.domainErrorFilter = domainErrorFilter;
    }

    @Override
    public FutureResult<Boolean> execute(MovieDetailDS movieDetailDS) {
        return localRepository.checkMovie(movieDetailDS.id)
                .flatMap(exist -> exist ? deleteMovie(movieDetailDS) : saveMovie(movieDetailDS));
    }

    private FutureResult<Boolean> saveMovie(MovieDetailDS movieDetailDS) {
        return localRepository.getNumberMovies()
                .flatMap(numberMovies -> {
                    if (numberMovies >= MAXIMUM_MOVIES_SAVED)
                        throw domainErrorFilter.filter(MovieFavoriteLimit.class);
                    return localRepository.saveMovie(MovieMapper.convert(movieDetailDS))
                            .toFutureResult(true);
                });
    }

    private FutureResult<Boolean> deleteMovie(MovieDetailDS movieDetailDS) {
        return localRepository.deleteMovie(movieDetailDS.id).toFutureResult(false);
    }

}