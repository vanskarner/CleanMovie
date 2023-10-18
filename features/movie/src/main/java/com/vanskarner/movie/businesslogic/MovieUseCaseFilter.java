package com.vanskarner.movie.businesslogic;

import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@SuppressWarnings("unchecked")
@Singleton
class MovieUseCaseFilter {
    private final Map<Class<?>, Provider<UseCase<?, ?>>> useCaseMap;

    @Inject
    public MovieUseCaseFilter(Map<Class<?>, Provider<UseCase<?, ?>>> useCaseMap) {
        this.useCaseMap = useCaseMap;
    }

    public <T extends UseCase<?, ?>> T filter(Class<T> classKey) {
        return (T) Objects.requireNonNull(useCaseMap.get(classKey)).get();
    }

}
