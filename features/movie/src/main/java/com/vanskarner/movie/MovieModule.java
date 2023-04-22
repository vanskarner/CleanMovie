package com.vanskarner.movie;

import com.vanskarner.movie.data.local.MovieLocalModule;
import com.vanskarner.movie.data.remote.MovieRemoteModule;
import com.vanskarner.movie.domain.services.MovieDomainModule;

import dagger.Module;

@Module(includes = {
        MovieDomainModule.class,
        MovieLocalModule.class,
        MovieRemoteModule.class
})
public class MovieModule {
}