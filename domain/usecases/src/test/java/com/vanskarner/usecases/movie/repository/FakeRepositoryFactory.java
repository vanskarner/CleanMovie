package com.vanskarner.usecases.movie.repository;

import com.vanskarner.entities.MovieBO;

import java.util.List;

public final class FakeRepositoryFactory {
    private FakeRepositoryFactory() {
    }

    public static MovieRemoteRepository createRemoteRepository(List<MovieBO> data) {
        return new FakeRemoteRepository(data);
    }

    public static MovieLocalRepository createLocalRepository() {
        return new FakeLocalRepository();
    }
}
