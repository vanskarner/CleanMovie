package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.List;

public class FakeRepositoryFactory {

    private FakeRepositoryFactory() {
    }

    public static MovieRemoteRepository createMovieRemoteRepository(List<MovieBO> data) {
        return new FakeRemoteRepository(data);
    }

    public static MovieLocalRepository createMovieLocalRepository() {
        return new FakeLocalRepository();
    }

}