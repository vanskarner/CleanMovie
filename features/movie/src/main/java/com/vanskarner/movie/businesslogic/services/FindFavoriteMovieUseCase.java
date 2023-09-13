package com.vanskarner.movie.businesslogic.services;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FindFavoriteMovieUseCase extends BaseAsyncUseCase<Integer, MovieDetailDS> {

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