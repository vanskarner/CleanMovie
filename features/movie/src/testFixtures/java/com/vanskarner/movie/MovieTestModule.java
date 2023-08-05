package com.vanskarner.movie;

import com.vanskarner.movie.persistence.local.MovieLocalModule;
import com.vanskarner.movie.persistence.remote.MovieRemoteDataTestModule;
import com.vanskarner.movie.businesslogic.services.MovieDomainModule;

import dagger.Module;

@Module(includes = {
        MovieLocalModule.class,
        MovieRemoteDataTestModule.class,
        MovieDomainModule.class
})
public class MovieTestModule {
}