package com.vanskarner.movie.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
abstract class TestRoomDB extends RoomDatabase {
    public abstract MovieDao movieDetailDao();
}