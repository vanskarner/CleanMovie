package com.vanskarner.cleanmovie.main.di.modules;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vanskarner.movie.persistence.local.MovieDao;
import com.vanskarner.movie.persistence.local.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
abstract class RoomDB extends RoomDatabase {

    public abstract MovieDao movieDetailDao();

}