package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;

public interface MovieRemoteRepository {

    FutureResult<MoviesDS> getMovies(int page);

    FutureResult<MovieDetailDS> getMovie(int movieId);

}