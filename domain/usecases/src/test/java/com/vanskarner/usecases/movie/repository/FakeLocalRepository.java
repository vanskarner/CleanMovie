package com.vanskarner.usecases.movie.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.entities.MovieBO;
import com.vanskarner.usecases.TestFuturesUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FakeLocalRepository implements MovieLocalRepository {
    private final TestFuturesUtils testFuturesUtils;
    private final List<MovieBO> data;

    public FakeLocalRepository(TestFuturesUtils testFuturesUtils, List<MovieBO> data) {
        this.testFuturesUtils = testFuturesUtils;
        this.data = data;
    }

    @Override
    public FutureResult<List<MovieBO>> getMovies() {
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

    @Override
    public FutureSimpleResult deleteMovie(int movieId) {
        return testFuturesUtils.completeSuccess();
    }

    @Override
    public FutureResult<Integer> deleteAllMovies() {
        return testFuturesUtils.fromData(data.size());
    }

    @Override
    public FutureResult<Integer> getNumberMovies() {
        return testFuturesUtils.fromData(data.size());
    }

    @Override
    public FutureResult<Boolean> checkMovie(int movieId) {
        Optional<MovieBO> optional = data
                .stream()
                .filter(item -> item.getId() == movieId)
                .findFirst();
        return testFuturesUtils.fromData(optional.isPresent());
    }

    @Override
    public FutureSimpleResult saveMovie(MovieBO movieDetail) {
        return testFuturesUtils.completeSuccess();
    }

}