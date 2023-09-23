package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;

public interface MovieRemoteRepository {

    FutureResult<MoviesDS> getMovies(int page);

    FutureResult<MovieDetailDS> getMovie(int movieId);

}