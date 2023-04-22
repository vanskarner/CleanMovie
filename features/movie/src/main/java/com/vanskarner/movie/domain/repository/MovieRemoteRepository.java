package com.vanskarner.movie.domain.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.domain.entities.MovieBO;

import java.util.List;

public interface MovieRemoteRepository {

    FutureResult<List<MovieBO>> getMovies(int page);

    FutureResult<MovieBO> getMovie(int movieId);

}