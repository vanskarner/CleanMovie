package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.ui.MoviesDS;

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