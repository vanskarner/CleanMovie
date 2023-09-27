package com.vanskarner.usecases.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.usecases.bases.BaseAsyncUseCase;
import com.vanskarner.usecases.movie.service.MoviesDS;

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