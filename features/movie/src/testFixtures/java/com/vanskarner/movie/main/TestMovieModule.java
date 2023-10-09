package com.vanskarner.movie.main;

import com.vanskarner.movie.persistence.local.MovieLocalRepositoryModule;
import com.vanskarner.movie.persistence.remote.TestMovieRemoteRepositoryModule;
import com.vanskarner.movie.businesslogic.MovieBusinessLogicModule;

import dagger.Module;

@Module(includes = {
        MovieBusinessLogicModule.class,
        MovieLocalRepositoryModule.class,
        TestMovieRemoteRepositoryModule.class
})
public class TestMovieModule {
}