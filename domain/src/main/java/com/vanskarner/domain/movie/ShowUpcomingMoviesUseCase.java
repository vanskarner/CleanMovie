package com.vanskarner.domain.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.domain.common.UseCase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ShowUpcomingMoviesUseCase extends UseCase<FutureResult<MoviesDS>, Integer> {

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