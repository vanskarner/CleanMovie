package com.vanskarner.domain.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.domain.common.UseCase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FindUpcomingMovieUseCase extends UseCase<FutureResult<MovieDetailDS>, Integer> {

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
                    detailDS.recommended = movieDetailBO.isRecommended();
                    return detailDS;
                });
    }

}