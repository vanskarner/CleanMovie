package com.vanskarner.domain.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.domain.bases.BaseAsyncOutPutUseCase;

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