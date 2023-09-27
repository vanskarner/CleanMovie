package com.vanskarner.domain.movie.service;

import com.vanskarner.domain.DomainError;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieFavoriteLimit extends DomainError {

    @Inject
    public MovieFavoriteLimit() {
    }

}