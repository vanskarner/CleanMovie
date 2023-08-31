package com.vanskarner.usecases.movie.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.core.concurrent.TestFutureFactory;
import com.vanskarner.core.concurrent.TestFutureSimpleFactory;
import com.vanskarner.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FakeLocalRepository implements MovieLocalRepository {
    private final List<MovieBO> data;

    public FakeLocalRepository() {
        this.data = new ArrayList<>();
    }

    @Override
    public FutureResult<List<MovieBO>> getMovies() {
        return TestFutureFactory.create(data);
    }

    @Override
    public FutureResult<MovieBO> getMovie(int movieId) {
        Optional<MovieBO> item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst();
        return item
                .map(TestFutureFactory::create)
                .orElseGet(() -> TestFutureFactory.create(new NoSuchElementException()));
    }

    @Override
    public FutureSimpleResult deleteMovie(int movieId) {
        Runnable runnable = () -> data.removeIf(item -> item.getId() == movieId);
        return TestFutureSimpleFactory.create(runnable);
    }

    @Override
    public FutureResult<Integer> deleteAllMovies() {
        int total = data.size();
        data.clear();
        return TestFutureFactory.create(total);
    }

    @Override
    public FutureResult<Integer> getNumberMovies() {
        return TestFutureFactory.create(data.size());
    }

    @Override
    public FutureResult<Boolean> checkMovie(int movieId) {
        Optional<MovieBO> item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst();
        return TestFutureFactory.create(item.isPresent());
    }

    @Override
    public FutureSimpleResult saveMovie(MovieBO movieDetail) {
        Runnable runnable = () -> data.add(movieDetail);
        return TestFutureSimpleFactory.create(runnable);
    }

}