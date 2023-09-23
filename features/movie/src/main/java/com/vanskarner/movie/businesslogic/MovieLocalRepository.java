package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.movie.MovieDetailDS;
import com.vanskarner.movie.MoviesDS;

public interface MovieLocalRepository {

    FutureResult<MoviesDS> getMovies();

    FutureResult<MovieDetailDS> getMovie(int movieId);

    FutureSimpleResult deleteMovie(int movieId);

    FutureResult<Integer> deleteAllMovies();

    FutureResult<Integer> getNumberMovies();

    FutureResult<Boolean> checkMovie(int movieId);

    FutureSimpleResult saveMovie(MovieDetailDS movieDetail);

}