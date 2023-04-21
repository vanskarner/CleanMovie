package com.vanskarner.usecases.movie.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.entities.MovieBO;
import com.vanskarner.usecases.TestFuturesUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FakeRemoteRepository implements MovieRemoteRepository {
    private final TestFuturesUtils testFuturesUtils;
    private final List<MovieBO> data;

    public FakeRemoteRepository(TestFuturesUtils testFuturesUtils, List<MovieBO> data) {
        this.testFuturesUtils = testFuturesUtils;
        this.data = data;
    }

    @Override
    public FutureResult<List<MovieBO>> getMovies(int page) {
        return testFuturesUtils.fromData(data);
    }

    @Override
    public FutureResult<MovieBO> getMovie(int movieId) {
        Optional<MovieBO> optional = data
                .stream()
                .filter(item -> item.getId() == movieId)
                .findFirst();
        return testFuturesUtils.fromDataOrElse(optional.orElse(null),
                new NoSuchElementException());
    }

}