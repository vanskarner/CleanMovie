package com.vanskarner.domain.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.domain.bases.BaseAsyncOutPutUseCase;
import com.vanskarner.domain.movie.service.MoviesDS;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ShowFavoriteMoviesUseCase extends BaseAsyncOutPutUseCase<MoviesDS> {

    private final MovieLocalRepository localRepository;

    @Inject
    public ShowFavoriteMoviesUseCase(MovieLocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public FutureResult<MoviesDS> execute() {
        return localRepository.getMovies().map(MovieMapper::convert);
    }

}