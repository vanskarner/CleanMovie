package com.vanskarner.movie.businesslogic;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/** @noinspection unused*/
@Module
public abstract class MovieBusinessLogicModule {

    @Binds
    @Singleton
    abstract MovieServices bindMovieServices(MovieDefaultServices movieDefaultServices);

}