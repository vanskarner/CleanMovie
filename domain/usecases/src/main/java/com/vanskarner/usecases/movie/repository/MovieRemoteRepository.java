package com.vanskarner.usecases.movie.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.entities.MovieBO;

import java.util.List;

public interface MovieRemoteRepository {

    FutureResult<List<MovieBO>> getMovies(int page);

    FutureResult<MovieBO> getMovie(int movieId);

}