package com.vanskarner.domain;

import com.vanskarner.domain.movie.service.MovieFavoriteLimit;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

public class MockDomainErrorFilter extends DomainErrorFilter {

    public MockDomainErrorFilter() {
        super(getMapError());
    }

    private static Map<Class<?>, Provider<DomainError>> getMapError() {
        Map<Class<?>, Provider<DomainError>> map = new HashMap<>();
        map.put(MovieFavoriteLimit.class, MovieFavoriteLimit::new);
        return map;
    }

}