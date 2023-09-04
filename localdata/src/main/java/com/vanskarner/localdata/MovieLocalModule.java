package com.vanskarner.localdata;

import com.vanskarner.usecases.movie.repository.MovieLocalRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/** @noinspection unused*/
@Module
abstract class MovieLocalModule {

    @Binds
    @Singleton
    abstract MovieLocalRepository bindMovieLocalRepository(MovieLocalRxRepository repository);

    @Provides
    @Singleton
    static MovieDao provideMovieDetailDao(RoomDB db) {
        return db.movieDao();
    }

}