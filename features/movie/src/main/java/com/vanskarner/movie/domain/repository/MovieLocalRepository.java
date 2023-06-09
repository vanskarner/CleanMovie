package com.vanskarner.movie.domain.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.movie.domain.entities.MovieBO;

import java.util.List;

public interface MovieLocalRepository {

    FutureResult<List<MovieBO>> getMovies();

    FutureResult<MovieBO> getMovie(int movieId);

    FutureSimpleResult deleteMovie(int movieId);

    FutureResult<Integer> deleteAllMovies();

    FutureResult<Integer> getNumberMovies();

    FutureResult<Boolean> checkMovie(int movieId);

    FutureSimpleResult saveMovie(MovieBO movieDetail);

}