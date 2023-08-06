package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.businesslogic.TestFuturesUtils;
import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.List;
import java.util.NoSuchElementException;

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
        MovieBO item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst()
                .orElse(null);
        return testFuturesUtils.fromDataOrElse(item, new NoSuchElementException());
    }

}