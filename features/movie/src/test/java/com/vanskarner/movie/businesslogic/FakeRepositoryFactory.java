package com.vanskarner.movie.businesslogic;

import com.vanskarner.movie.MovieDetailDS;

import java.util.List;

public final class FakeRepositoryFactory {

    private FakeRepositoryFactory() {
    }

    public static MovieRemoteRepository createRemoteRepository(List<MovieDetailDS> data) {
        return new FakeRemoteRepository(data);
    }

    public static MovieLocalRepository createLocalRepository() {
        return new FakeLocalRepository();
    }

}