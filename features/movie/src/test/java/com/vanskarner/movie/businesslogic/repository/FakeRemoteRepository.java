package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.concurrent.TestFutureFactory;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

class FakeRemoteRepository implements MovieRemoteRepository {
    private final List<MovieBO> data;

    public FakeRemoteRepository(List<MovieBO> data) {
        this.data = data;
    }

    @Override
    public FutureResult<List<MovieBO>> getMovies(int page) {
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

}