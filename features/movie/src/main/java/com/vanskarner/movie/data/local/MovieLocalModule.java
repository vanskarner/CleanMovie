package com.vanskarner.movie.data.local;

import com.vanskarner.movie.domain.repository.MovieLocalRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieLocalModule {

    @Binds
    @Singleton
    abstract MovieLocalRepository bindMovieLocalRepository(MovieLocalRxRepository repository);

}