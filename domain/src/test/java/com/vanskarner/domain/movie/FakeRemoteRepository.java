package com.vanskarner.domain.movie;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.TestFutureFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FakeRemoteRepository implements MovieRemoteRepository {
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