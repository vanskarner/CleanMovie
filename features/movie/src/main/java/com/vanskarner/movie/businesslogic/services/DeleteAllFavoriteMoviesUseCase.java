package com.vanskarner.movie.businesslogic.services;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.businesslogic.bases.BaseAsyncOutPutUseCase;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

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