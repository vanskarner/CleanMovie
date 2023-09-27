package com.vanskarner.localdata.movie;

import com.vanskarner.domain.movie.MovieLocalRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/** @noinspection unused*/
@Module
public abstract class MovieLocalModule {

    @Binds
    @Singleton
    abstract MovieLocalRepository bindMovieLocalRepository(MovieLocalRxRepository repository);

}