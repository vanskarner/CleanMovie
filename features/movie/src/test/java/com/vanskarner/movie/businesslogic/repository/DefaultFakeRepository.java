package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.concurrent.FutureFactory;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.core.concurrent.rxjava.DefaultFutureFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DefaultFakeRepository implements FakeRepository {
    private final CompositeDisposable compositeDisposable;
    private final FutureFactory futureFactory;
    private final List<MovieBO> data;

    public DefaultFakeRepository() {
        this.compositeDisposable = new CompositeDisposable();
        this.futureFactory = new DefaultFutureFactory(
                compositeDisposable,
                Schedulers.trampoline(),
                Schedulers.trampoline());
        this.data = new ArrayList<>();
    }

    @Override
    public FutureResult<List<MovieBO>> getMovies() {
        return futureFactory.just(data);
    }

    @Override
    public FutureResult<List<MovieBO>> getMovies(int page) {
        return futureFactory.just(data);
    }

    @Override
    public FutureResult<MovieBO> getMovie(int movieId) {
        MovieBO item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst()
                .orElse(null);
        return futureFactory.fromCallable(() -> {
            if (item != null)
                return item;
            throw new NoSuchElementException();
        });
    }

    @Override
    public FutureSimpleResult deleteMovie(int movieId) {
        data.removeIf(movieBO -> movieBO.getId() == movieId);
        return futureFactory.success();
    }

    @Override
    public FutureResult<Integer> deleteAllMovies() {
        int total = data.size();
        data.clear();
        return futureFactory.just(total);
    }

    @Override
    public FutureResult<Integer> getNumberMovies() {
        return futureFactory.just(data.size());
    }

    @Override
    public FutureResult<Boolean> checkMovie(int movieId) {
        Optional<MovieBO> optional = data
                .stream()
                .filter(item -> item.getId() == movieId)
                .findFirst();
        return futureFactory.just(optional.isPresent());
    }

    @Override
    public FutureSimpleResult saveMovie(MovieBO movieDetail) {
        data.add(movieDetail);
        return futureFactory.success();
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}