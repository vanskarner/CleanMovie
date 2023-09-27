package com.vanskarner.localdata.movie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class MovieRoomDB extends RoomDatabase {
    public abstract MovieDao movieDao();
}
