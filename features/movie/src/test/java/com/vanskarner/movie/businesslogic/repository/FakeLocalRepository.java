package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.concurrent.TestFutureResult;
import com.vanskarner.core.concurrent.TestFutureSimpleResult;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
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
        return new TestFutureResult<>(data);
    }

    @Override
    public FutureResult<MovieBO> getMovie(int movieId) {
        Optional<MovieBO> item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst();
        return item
                .<FutureResult<MovieBO>>map(TestFutureResult::new)
                .orElseGet(() -> new TestFutureResult<>(new NoSuchElementException()));
    }

    @Override
    public FutureSimpleResult deleteMovie(int movieId) {
        return new TestFutureSimpleResult(() -> data.removeIf(item -> item.getId() == movieId));
    }

    @Override
    public FutureResult<Integer> deleteAllMovies() {
        int total = data.size();
        data.clear();
        return new TestFutureResult<>(total);
    }

    @Override
    public FutureResult<Integer> getNumberMovies() {
        return new TestFutureResult<>(data.size());
    }

    @Override
    public FutureResult<Boolean> checkMovie(int movieId) {
        Optional<MovieBO> item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst();
        return new TestFutureResult<>(item.isPresent());
    }

    @Override
    public FutureSimpleResult saveMovie(MovieBO movieDetail) {
        return new TestFutureSimpleResult(() -> data.add(movieDetail));
    }

}
