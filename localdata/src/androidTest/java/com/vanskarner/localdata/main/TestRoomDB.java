package com.vanskarner.localdata.main;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vanskarner.localdata.movie.MovieDao;
import com.vanskarner.localdata.movie.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class TestRoomDB extends RoomDatabase {
    public abstract MovieDao movieDao();
}
