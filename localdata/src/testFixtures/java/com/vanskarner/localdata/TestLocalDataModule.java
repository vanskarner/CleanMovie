package com.vanskarner.localdata;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MovieLocalModule.class})
public abstract class TestLocalDataModule {

    @Provides
    @Singleton
    static RoomDB provideDBInstance(Context context) {
        return Room
                .inMemoryDatabaseBuilder(context, RoomDB.class)
                .fallbackToDestructiveMigration()
                .build();
    }

}