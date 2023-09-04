package com.vanskarner.localdata;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/** @noinspection unused*/
@Module(includes = {
        MovieLocalModule.class
})
public abstract class LocalDataModule {

    private static final String DATABASE_NAME = "CleanMovie";

    @Provides
    @Singleton
    static RoomDB provideDB(Context context) {
        return Room
                .databaseBuilder(context, RoomDB.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

}