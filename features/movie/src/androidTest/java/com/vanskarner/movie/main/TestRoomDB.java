package com.vanskarner.movie.main;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vanskarner.movie.persistence.local.MovieDao;
import com.vanskarner.movie.persistence.local.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class TestRoomDB extends RoomDatabase {
    public abstract MovieDao movieDetailDao();
}