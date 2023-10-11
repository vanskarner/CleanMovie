package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ShowFavoriteMoviesUseCase extends UseCase<FutureResult<MoviesDS>,Void> {

    private final MovieLocalRepository localRepository;

    @Inject
    public ShowFavoriteMoviesUseCase(MovieLocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public FutureResult<MoviesDS> execute(Void unused) {
        return localRepository.getMovies().map(MovieMapper::convert);
    }

}