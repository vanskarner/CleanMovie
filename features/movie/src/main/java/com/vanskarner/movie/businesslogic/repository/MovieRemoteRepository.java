package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.List;

public interface MovieRemoteRepository {

    FutureResult<List<MovieBO>> getMovies(int page);

    FutureResult<MovieBO> getMovie(int movieId);

}