package com.vanskarner.movie;

import com.vanskarner.movie.data.local.MovieLocalModule;
import com.vanskarner.movie.data.remote.MovieRemoteDataTestModule;
import com.vanskarner.movie.domain.services.MovieDomainModule;

import dagger.Module;

@Module(includes = {
        MovieLocalModule.class,
        MovieRemoteDataTestModule.class,
        MovieDomainModule.class
})
public class MovieTestModule {
}