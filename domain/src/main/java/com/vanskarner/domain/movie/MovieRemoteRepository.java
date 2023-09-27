package com.vanskarner.domain.movie;

import com.vanskarner.core.concurrent.FutureResult;


import java.util.List;

public interface MovieRemoteRepository {

    FutureResult<List<MovieBO>> getMovies(int page);

    FutureResult<MovieBO> getMovie(int movieId);

}