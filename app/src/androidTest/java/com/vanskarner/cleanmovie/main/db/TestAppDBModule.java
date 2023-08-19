package com.vanskarner.cleanmovie.main.db;

import android.content.Context;

import androidx.room.Room;

import com.vanskarner.movie.persistence.local.MovieDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class TestAppDBModule {

    @Provides
    @Singleton
    static MovieDao provideMovieDetailDao(RoomDB db) {
        return db.movieDetailDao();
    }

    @Provides
    @Singleton
    static RoomDB provideDB(Context context) {
        return Room
                .inMemoryDatabaseBuilder(context, RoomDB.class)
                .fallbackToDestructiveMigration()
                .build();
    }

}