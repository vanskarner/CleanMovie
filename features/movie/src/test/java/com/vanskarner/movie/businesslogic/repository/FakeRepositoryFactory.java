package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.movie.businesslogic.entities.MovieBO;

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