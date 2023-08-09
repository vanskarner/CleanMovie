package com.vanskarner.movie.businesslogic.repository;

public interface FakeRepository extends MovieLocalRepository, MovieRemoteRepository {

    void clear();

}