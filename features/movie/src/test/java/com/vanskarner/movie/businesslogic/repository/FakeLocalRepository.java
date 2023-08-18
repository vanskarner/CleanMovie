package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.SyncFutureResult;
import com.vanskarner.core.SyncFutureSimpleResult;
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
        return new SyncFutureResult<>(data);
    }

    @Override
    public FutureResult<MovieBO> getMovie(int movieId) {
        Optional<MovieBO> item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst();
        return item
                .<FutureResult<MovieBO>>map(SyncFutureResult::new)
                .orElseGet(() -> new SyncFutureResult<>(new NoSuchElementException()));
    }

    @Override
    public FutureSimpleResult deleteMovie(int movieId) {
        return new SyncFutureSimpleResult(() -> data.removeIf(item -> item.getId() == movieId));
    }

    @Override
    public FutureResult<Integer> deleteAllMovies() {
        int total = data.size();
        data.clear();
        return new SyncFutureResult<>(total);
    }

    @Override
    public FutureResult<Integer> getNumberMovies() {
        return new SyncFutureResult<>(data.size());
    }

    @Override
    public FutureResult<Boolean> checkMovie(int movieId) {
        Optional<MovieBO> item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst();
        return new SyncFutureResult<>(item.isPresent());
    }

    @Override
    public FutureSimpleResult saveMovie(MovieBO movieDetail) {
        return new SyncFutureSimpleResult(() -> data.add(movieDetail));
    }

}
