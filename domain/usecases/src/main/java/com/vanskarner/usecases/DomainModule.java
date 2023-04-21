package com.vanskarner.usecases;

import com.vanskarner.usecases.movie.MovieServicesModule;

import dagger.Module;

@Module(includes = {
        MovieServicesModule.class
})
public abstract class DomainModule {
}