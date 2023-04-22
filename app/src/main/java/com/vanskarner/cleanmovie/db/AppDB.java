package com.vanskarner.cleanmovie.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vanskarner.movie.data.local.MovieDao;
import com.vanskarner.movie.data.local.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
abstract class AppDB extends RoomDatabase {

    public abstract MovieDao movieDetailDao();

}