package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.TestFutureFactory;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.movie.MovieBasicDS;
import com.vanskarner.movie.MovieDetailDS;
import com.vanskarner.movie.MoviesDS;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

class FakeRemoteRepository implements MovieRemoteRepository {
    private final List<MovieDetailDS> data;

    public FakeRemoteRepository(List<MovieDetailDS> data) {
        this.data = data;
    }

    @Override
    public FutureResult<MoviesDS> getMovies(int page) {
        List<MovieBasicDS> list = new ArrayList<>();
        for (MovieDetailDS detail : data)
            list.add(new MovieBasicDS(detail.basicInfo.id, detail.basicInfo.title, detail.basicInfo.image));
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

}
