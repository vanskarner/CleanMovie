package com.vanskarner.domain.movie;

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
