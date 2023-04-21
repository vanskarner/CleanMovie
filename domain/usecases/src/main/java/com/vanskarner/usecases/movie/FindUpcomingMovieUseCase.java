package com.vanskarner.usecases.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.usecases.bases.BaseAsyncUseCase;
import com.vanskarner.usecases.movie.ds.MovieDetailDS;
import com.vanskarner.usecases.movie.repository.MovieRemoteRepository;

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
                .map(movieDetailBO -> {
                    MovieDetailDS detailDS = MovieMapper.convert(movieDetailBO);
                    detailDS.recommended = movieDetailBO.calculateRecommendation();
                    return detailDS;
                });
    }

}