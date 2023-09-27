package com.vanskarner.usecases.main;

import com.vanskarner.usecases.movie.MovieServicesModule;

import dagger.Module;

@Module(includes = {
        MovieServicesModule.class
})
public abstract class DomainModule {
}