package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class CheckFavoriteMovieUseCase extends BaseAsyncUseCase<Integer, Boolean> {

    private final MovieLocalRepository localRepository;

    @Inject
    public CheckFavoriteMovieUseCase(MovieLocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public FutureResult<Boolean> execute(Integer inputValues) {
        return localRepository.checkMovie(inputValues);
    }

}