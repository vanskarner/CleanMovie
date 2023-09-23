package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.MovieDetailDS;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FindUpcomingMovieUseCase extends BaseAsyncUseCase<Integer, MovieDetailDS> {

    private final MovieRemoteRepository remoteRepository;

    @Inject
    public FindUpcomingMovieUseCase(MovieRemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    public FutureResult<MovieDetailDS> execute(Integer inputValues) {
        return remoteRepository.getMovie(inputValues)
                .map(item -> {
                    MovieBO movieBO = new MovieBO(item.voteCount, item.voteAverage);
                    item.recommended = movieBO.isRecommended();
                    return item;
                });
    }

}