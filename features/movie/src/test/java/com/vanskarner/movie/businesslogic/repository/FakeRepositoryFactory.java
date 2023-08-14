package com.vanskarner.movie.businesslogic.repository;

public class FakeRepositoryFactory {

    private FakeRepositoryFactory() {
    }

    public static FakeMovieRemoteRepository createMovieRemoteRepository() {
        return new DefaultFakeMovieRemoteRepository();
    }

    public static FakeMovieLocalRepository createMovieLocalRepository() {
        return new DefaultFakeMovieLocalRepository();
    }

}