package com.vanskarner.movie.persistence.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(MovieEntity movieEntity);

    @Query("SELECT * FROM movie_detail WHERE id=:id LIMIT 1")
    Single<MovieEntity> find(int id);

    @Query("SELECT * FROM movie_detail")
    Single<List<MovieEntity>> toList();

    @Query("DELETE FROM movie_detail WHERE id=:id")
    Completable deleteItem(int id);

    @Query("DELETE FROM movie_detail")
    Single<Integer> deleteAll();

    @Query("SELECT COUNT(id) FROM movie_detail")
    Single<Integer> getQuantity();

    @Query("SELECT EXISTS(SELECT * FROM movie_detail WHERE id=:id LIMIT 1)")
    Single<Boolean> checkExistence(int id);

}