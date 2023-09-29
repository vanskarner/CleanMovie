package com.vanskarner.movie.persistence.local;

import com.vanskarner.movie.businesslogic.MovieLocalRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/** @noinspection unused*/
@Module
public abstract class MovieLocalRepositoryModule {

    @Binds
    @Singleton
    abstract MovieLocalRepository bindMovieLocalRepository(MovieLocalRxRepository repository);

}