package com.vanskarner.usecases.movie;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module(includes = MovieErrorModule.class)
public abstract class MovieServicesModule {

    @Binds
    @Singleton
    abstract MovieServices bindMovieServices(MovieDefaultServices movieDefaultServices);

}