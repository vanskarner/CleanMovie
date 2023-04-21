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
class ActionFavoriteMovieUseCase extends BaseAsyncUseCase<MovieDetailDS, Boolean> {
    private final static int MAXIMUM_MOVIES_SAVED = 2;
    private final MovieLocalRepository localRepository;
    private final DomainErrorFilter domainErrorFilter;

    @Inject
    public ActionFavoriteMovieUseCase(
            MovieLocalRepository localRepository,
            DomainErrorFilter domainErrorFilter) {
        this.localRepository = localRepository;
        this.domainErrorFilter = domainErrorFilter;
    }

    @Override
    public FutureResult<Boolean> execute(MovieDetailDS movieDetailDS) {
        FutureResult<Boolean> deleteItem = localRepository.deleteMovie(movieDetailDS.id)
                .toFutureResult(false);
        FutureResult<Boolean> saveItem = localRepository.getNumberMovies()
                .flatMap(integer -> {
                    if (integer >= MAXIMUM_MOVIES_SAVED)
                        throw domainErrorFilter.filter(MovieFavoriteLimit.class);
                    return localRepository.saveMovie(MovieMapper.convert(movieDetailDS))
                            .toFutureResult(true);
                });
        return localRepository.checkMovie(movieDetailDS.id)
                .flatMap(exists -> exists ? deleteItem : saveItem);
    }

}