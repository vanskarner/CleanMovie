package com.vanskarner.movie.main;

import com.vanskarner.movie.persistence.local.MovieLocalRepositoryModule;
import com.vanskarner.movie.persistence.remote.TestMovieRemoteRepositoryModule;
import com.vanskarner.movie.businesslogic.MovieBusinessLogicModule;

import dagger.Module;

@Module(includes = {
        MovieLocalRepositoryModule.class,
        TestMovieRemoteRepositoryModule.class,
        MovieBusinessLogicModule.class
})
public class TestMovieModule {
}