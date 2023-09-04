package com.vanskarner.movie.businesslogic.services;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/** @noinspection unused*/
@Module(includes = MovieErrorModule.class)
public abstract class MovieBusinessLogicModule {

    @Binds
    @Singleton
    abstract MovieServices bindMovieServices(MovieDefaultServices movieDefaultServices);

}