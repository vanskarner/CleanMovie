package com.vanskarner.movie.persistence.local;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.FutureSimpleResult;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;
import com.vanskarner.movie.businesslogic.MovieDetailDS;
import com.vanskarner.movie.businesslogic.MoviesDS;
import com.vanskarner.movie.businesslogic.MovieLocalRepository;
import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

class MovieLocalRxRepository implements MovieLocalRepository {
    private final RxFutureFactory rxFutureFactory;
    private final MovieDao dao;

    @Inject
    public MovieLocalRxRepository(
            RxFutureFactory rxFutureFactory,
            MovieDao dao) {
        this.rxFutureFactory = rxFutureFactory;
        this.dao = dao;
    }

    @Override
    public FutureResult<MoviesDS> getMovies() {
        Single<MoviesDS> single = dao.toList().map(MovieLocalDataMapper::convert);
        return rxFutureFactory.fromSingle(single);
    }

    @Override
    public FutureResult<MovieDetailDS> getMovie(int movieId) {
        Single<MovieDetailDS> single = dao.find(movieId).map(MovieLocalDataMapper::convert);
        return rxFutureFactory.fromSingle(single);
    }

    @Override
    public FutureSimpleResult deleteMovie(int movieId) {
        Completable completable = dao.deleteItem(movieId);
        return rxFutureFactory.fromCompletable(completable);
    }

    @Override
    public FutureResult<Integer> deleteAllMovies() {
        Single<Integer> single = dao.deleteAll();
        return rxFutureFactory.fromSingle(single);
    }

    @Override
    public FutureResult<Integer> getNumberMovies() {
        Single<Integer> single = dao.getQuantity();
        return rxFutureFactory.fromSingle(single);
    }

    @Override
    public FutureResult<Boolean> checkMovie(int movieId) {
        Single<Boolean> single = dao.checkExistence(movieId);
        return rxFutureFactory.fromSingle(single);
    }

    @Override
    public FutureSimpleResult saveMovie(MovieDetailDS movieDetail) {
        Completable completable = dao.insert(MovieLocalDataMapper.convert(movieDetail));
        return rxFutureFactory.fromCompletable(completable);
    }

}