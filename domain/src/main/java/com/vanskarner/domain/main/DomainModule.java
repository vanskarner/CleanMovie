package com.vanskarner.domain.main;

import com.vanskarner.domain.movie.MovieServicesModule;

import dagger.Module;

@Module(includes = {
        MovieServicesModule.class
})
public abstract class DomainModule {
}