package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.core.concurrent.TestFutureFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

class FakeLocalRepository implements MovieLocalRepository {

    private final List<MovieBO> data;

    public FakeLocalRepository() {
        this.data = new ArrayList<>();
    }

    @Override
    public FutureResult<List<MovieBO>> getMovies() {
        return TestFutureFactory.createSuccess(data);
    }

    @Override
    public FutureResult<MovieBO> getMovie(int movieId) {
        Optional<MovieBO> item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst();
        return item
                .map(TestFutureFactory::createSuccess)
                .orElseGet(() -> TestFutureFactory.createFail(new NoSuchElementException()));
    }

    @Override
    public FutureSimpleResult deleteMovie(int movieId) {
        Runnable runnable = () -> data.removeIf(item -> item.getId() == movieId);
        return TestFutureFactory.createSimpleSuccess(runnable);
    }

    @Override
    public FutureResult<Integer> deleteAllMovies() {
        int total = data.size();
        data.clear();
        return TestFutureFactory.createSuccess(total);
    }

    @Override
    public FutureResult<Integer> getNumberMovies() {
        return TestFutureFactory.createSuccess(data.size());
    }

    @Override
    public FutureResult<Boolean> checkMovie(int movieId) {
        Optional<MovieBO> item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst();
        return TestFutureFactory.createSuccess(item.isPresent());
    }

    @Override
    public FutureSimpleResult saveMovie(MovieBO movieDetail) {
        Runnable runnable = () -> data.add(movieDetail);
        return TestFutureFactory.createSimpleSuccess(runnable);
    }

}
