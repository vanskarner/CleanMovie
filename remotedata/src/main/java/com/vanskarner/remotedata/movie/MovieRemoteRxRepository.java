package com.vanskarner.remotedata.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;
import com.vanskarner.entities.MovieBO;
import com.vanskarner.remotedata.main.MovieRemoteDataQualifiers;
import com.vanskarner.usecases.movie.repository.MovieRemoteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

class MovieRemoteRxRepository implements MovieRemoteRepository {

    private final RxFutureFactory rxFutureFactory;
    private final MovieApiClient service;
    private final String apiKey;

    @Inject
    public MovieRemoteRxRepository(
            RxFutureFactory rxFutureFactory,
            MovieApiClient service,
            @MovieRemoteDataQualifiers.Apikey String apiKey) {
        this.rxFutureFactory = rxFutureFactory;
        this.service = service;
        this.apiKey = apiKey;
    }

    @Override
    public FutureResult<List<MovieBO>> getMovies(int page) {
        Single<List<MovieBO>> single = service
                .getUpcomingMovies(page, apiKey)
                .map(moviesResultDTO -> moviesResultDTO.results)
                .map(MovieRemoteDataMapper::convert);
        return rxFutureFactory.fromSingle(single);
    }

    @Override
    public FutureResult<MovieBO> getMovie(int movieId) {
        Single<MovieBO> single = service
                .getMovieDetail(movieId, apiKey)
                .map(MovieRemoteDataMapper::convert);
        return rxFutureFactory.fromSingle(single);
    }

}