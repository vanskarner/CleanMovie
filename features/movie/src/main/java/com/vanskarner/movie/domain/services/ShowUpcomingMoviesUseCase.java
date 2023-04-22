package com.vanskarner.movie.domain.services;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.domain.bases.BaseAsyncUseCase;
import com.vanskarner.movie.domain.ds.MoviesDS;
import com.vanskarner.movie.domain.repository.MovieRemoteRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ShowUpcomingMoviesUseCase extends BaseAsyncUseCase<Integer, MoviesDS> {

    private final MovieRemoteRepository remoteRepository;

    @Inject
    public ShowUpcomingMoviesUseCase(MovieRemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    public FutureResult<MoviesDS> execute(Integer page) {
        return remoteRepository.getMovies(page).map(MovieMapper::convert);
    }

}