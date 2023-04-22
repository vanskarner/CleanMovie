package com.vanskarner.movie.domain.services;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.domain.bases.BaseAsyncUseCase;
import com.vanskarner.movie.domain.repository.MovieLocalRepository;

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