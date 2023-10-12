package com.vanskarner.cleanmovie.main.di.modules;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vanskarner.localdata.movie.MovieDao;
import com.vanskarner.localdata.movie.MovieEntity;

@Database(entities = {MovieEntity.class},
        version = 1,
        exportSchema = false)
abstract class RoomDB extends RoomDatabase {

    public abstract MovieDao movieDao();

}