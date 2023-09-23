package com.vanskarner.movie.main;

import com.vanskarner.movie.persistence.local.MovieLocalRepositoryModule;
import com.vanskarner.movie.persistence.remote.MovieRemoteRepositoryModule;
import com.vanskarner.movie.businesslogic.MovieBusinessLogicModule;

import dagger.Module;

@Module(includes = {
        MovieBusinessLogicModule.class,
        MovieLocalRepositoryModule.class,
        MovieRemoteRepositoryModule.class,
        MovieErrorModule.class
})
public class MovieModule {
}