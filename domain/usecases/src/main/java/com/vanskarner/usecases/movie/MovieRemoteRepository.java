package com.vanskarner.usecases.movie;

import com.vanskarner.core.concurrent.FutureResult;


import java.util.List;

public interface MovieRemoteRepository {

    FutureResult<List<MovieBO>> getMovies(int page);

    FutureResult<MovieBO> getMovie(int movieId);

}