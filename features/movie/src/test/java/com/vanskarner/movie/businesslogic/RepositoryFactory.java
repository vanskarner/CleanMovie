package com.vanskarner.movie.businesslogic;

import com.vanskarner.movie.businesslogic.repository.DefaultFakeRepository;
import com.vanskarner.movie.businesslogic.repository.FakeRepository;

public class RepositoryFactory {
    public static FakeRepository createRepository() {
        return new DefaultFakeRepository();
    }

}