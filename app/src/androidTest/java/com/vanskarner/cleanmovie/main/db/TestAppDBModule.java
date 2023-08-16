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
    static MovieDao provideMovieDetailDao(AppDB db) {
        return db.movieDetailDao();
    }

    @Provides
    @Singleton
    static AppDB provideDB(Context context) {
        return Room
                .inMemoryDatabaseBuilder(context, AppDB.class)
                .fallbackToDestructiveMigration()
                .build();
    }

}