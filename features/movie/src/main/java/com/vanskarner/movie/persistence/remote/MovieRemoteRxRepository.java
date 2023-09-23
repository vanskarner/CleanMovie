package com.vanskarner.movie.persistence.remote;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;
import com.vanskarner.movie.MovieDetailDS;
import com.vanskarner.movie.MoviesDS;
import com.vanskarner.movie.businesslogic.MovieRemoteRepository;
import com.vanskarner.movie.main.MovieRemoteDataQualifiers;

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
    public FutureResult<MoviesDS> getMovies(int page) {
        Single<MoviesDS> single = service
                .getUpcomingMovies(page, apiKey)
                .map(moviesResultDTO -> moviesResultDTO.results)
                .map(MovieRemoteDataMapper::convert);
        return rxFutureFactory.fromSingle(single);
    }

    @Override
    public FutureResult<MovieDetailDS> getMovie(int movieId) {
        Single<MovieDetailDS> single = service
                .getMovieDetail(movieId, apiKey)
                .map(MovieRemoteDataMapper::convert);
        return rxFutureFactory.fromSingle(single);
    }

}