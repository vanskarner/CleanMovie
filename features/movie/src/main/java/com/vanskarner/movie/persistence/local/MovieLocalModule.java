package com.vanskarner.movie.persistence.local;

import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieLocalModule {

    @Binds
    @Singleton
    abstract MovieLocalRepository bindMovieLocalRepository(MovieLocalRxRepository repository);

}