package com.vanskarner.movie;

import com.vanskarner.movie.persistence.local.MovieLocalModule;
import com.vanskarner.movie.persistence.remote.MovieRemoteModule;
import com.vanskarner.movie.businesslogic.services.MovieDomainModule;

import dagger.Module;

@Module(includes = {
        MovieDomainModule.class,
        MovieLocalModule.class,
        MovieRemoteModule.class
})
public class MovieModule {
}