package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class DeleteAllFavoriteMoviesUseCase extends UseCase<FutureResult<Integer>, Void> {

    private final MovieLocalRepository localRepository;

    @Inject
    public DeleteAllFavoriteMoviesUseCase(MovieLocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public FutureResult<Integer> execute(Void unused) {
        return localRepository.deleteAllMovies();
    }
}