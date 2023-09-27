package com.vanskarner.usecases.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.usecases.bases.BaseAsyncOutPutUseCase;
import com.vanskarner.usecases.movie.service.MoviesDS;

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