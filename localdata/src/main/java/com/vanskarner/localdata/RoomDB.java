package com.vanskarner.localdata;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class},
        version = 1,
        exportSchema = false)
abstract class RoomDB extends RoomDatabase {

    public abstract MovieDao movieDao();

}