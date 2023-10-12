package com.vanskarner.cleanmovie.main.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.vanskarner.localdata.movie.MovieDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/** @noinspection unused*/
@Module
public abstract class TestRoomDBModule {
    @Provides
    @Singleton
    public static MovieDao provideMovieDetailDao(RoomDB db) {
        return db.movieDao();
    }

    @Provides
    @Singleton
    public static RoomDB provideDB(Context context) {
        return Room
                .inMemoryDatabaseBuilder(context, RoomDB.class)
                .fallbackToDestructiveMigration()
                .build();
    }

}