package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.core.concurrent.FutureFactory;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.rxjava.DefaultFutureFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DefaultFakeMovieRemoteRepository implements FakeMovieRemoteRepository {
    private final CompositeDisposable compositeDisposable;
    private final FutureFactory futureFactory;
    private final List<MovieBO> data;

    public DefaultFakeMovieRemoteRepository() {
        this.compositeDisposable = new CompositeDisposable();
        this.futureFactory = new DefaultFutureFactory(
                compositeDisposable,
                Schedulers.trampoline(),
                Schedulers.trampoline());
        this.data = new ArrayList<>();
    }

    @Override
    public FutureResult<List<MovieBO>> getMovies(int page) {
        return futureFactory.just(data);
    }

    @Override
    public FutureResult<MovieBO> getMovie(int movieId) {
        Optional<MovieBO> item = data
                .stream()
                .filter(i -> i.getId() == movieId)
                .findFirst();
        return futureFactory.fromCallable(() -> {
            if (item.isPresent())
                return item.get();
            throw new NoSuchElementException();
        });
    }

    @Override
    public void addItem(MovieBO item) {
        data.add(item);
    }

    @Override
    public void setList(List<MovieBO> list) {
        data.clear();
        data.addAll(list);
    }

    @Override
    public List<MovieBO> getList() {
        return data;
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}