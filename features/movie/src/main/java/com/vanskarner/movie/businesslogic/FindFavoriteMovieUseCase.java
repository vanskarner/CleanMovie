package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FindFavoriteMovieUseCase extends UseCase<FutureResult<MovieDetailDS>, Integer> {

    private final MovieLocalRepository localRepository;

    @Inject
    public FindFavoriteMovieUseCase(MovieLocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public FutureResult<MovieDetailDS> execute(Integer inputValues) {
        return localRepository.getMovie(inputValues);
    }

}