package com.vanskarner.usecases.movie.service;

import com.vanskarner.usecases.DomainError;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieFavoriteLimit extends DomainError {

    @Inject
    public MovieFavoriteLimit() {
    }

}