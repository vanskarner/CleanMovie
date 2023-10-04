package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class DeleteAllFavoriteMoviesUseCase extends BaseAsyncOutPutUseCase<Integer> {

    private final MovieLocalRepository localRepository;

    @Inject
    public DeleteAllFavoriteMoviesUseCase(MovieLocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public FutureResult<Integer> execute() {
        return localRepository.deleteAllMovies();
    }

}