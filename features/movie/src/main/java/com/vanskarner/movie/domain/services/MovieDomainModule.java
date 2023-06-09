package com.vanskarner.movie.domain.services;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module(includes = MovieErrorModule.class)
public abstract class MovieDomainModule {

    @Binds
    @Singleton
    abstract MovieServices bindMovieServices(MovieDefaultServices movieDefaultServices);

}