package com.vanskarner.cleanmovie.db;

import android.content.Context;

import androidx.room.Room;

import com.vanskarner.movie.data.local.MovieDao;

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