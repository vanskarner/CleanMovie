package com.vanskarner.movie.domain.services;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.domain.bases.BaseAsyncOutPutUseCase;
import com.vanskarner.movie.domain.ds.MoviesDS;
import com.vanskarner.movie.domain.repository.MovieLocalRepository;

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