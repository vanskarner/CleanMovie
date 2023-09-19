package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.core.concurrent.TestFutureFactory;
import com.vanskarner.core.concurrent.TestFutureSimpleFactory;
import com.vanskarner.movie.businesslogic.ds.MovieBasicDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

class FakeLocalRepository implements MovieLocalRepository {

    private final List<MovieDetailDS> data;

    public FakeLocalRepository() {
        this.data = new ArrayList<>();
    }

    @Override
    public FutureResult<MoviesDS> getMovies() {
        List<MovieBasicDS> list = new ArrayList<>();
        for (MovieDetailDS detail : data)
            list.add(new MovieBasicDS(
                    detail.basicInfo.id,
                    detail.basicInfo.title,
                    detail.basicInfo.image));
        MoviesDS moviesDS = new MoviesDS(list);
        return TestFutureFactory.create(moviesDS);
    }

    @Override
    public FutureResult<MovieDetailDS> getMovie(int movieId) {
        Optional<MovieDetailDS> item = data
                .stream()
                .filter(i -> i.basicInfo.id == movieId)
                .findFirst();
        return item
                .map(TestFutureFactory::create)
                .orElseGet(() -> TestFutureFactory.create(new NoSuchElementException()));
    }

    @Override
    public FutureSimpleResult deleteMovie(int movieId) {
        Runnable runnable = () -> data.removeIf(item -> item.basicInfo.id == movieId);
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
        Optional<MovieDetailDS> item = data
                .stream()
                .filter(i -> i.basicInfo.id == movieId)
                .findFirst();
        return TestFutureFactory.create(item.isPresent());
    }

    @Override
    public FutureSimpleResult saveMovie(MovieDetailDS movieDetail) {
        Runnable runnable = () -> data.add(movieDetail);
        return TestFutureSimpleFactory.create(runnable);
    }

}
