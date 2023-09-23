package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.MovieDetailDS;
import com.vanskarner.movie.MoviesDS;

public interface MovieRemoteRepository {

    FutureResult<MoviesDS> getMovies(int page);

    FutureResult<MovieDetailDS> getMovie(int movieId);

}