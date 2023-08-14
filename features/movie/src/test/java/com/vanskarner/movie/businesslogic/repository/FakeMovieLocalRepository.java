package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.List;

public interface FakeMovieLocalRepository extends MovieLocalRepository {

    void addItem(MovieBO item);

    void setList(List<MovieBO> list);

    List<MovieBO> getList();

    void clear();

}