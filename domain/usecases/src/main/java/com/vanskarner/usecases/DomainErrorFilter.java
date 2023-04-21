package com.vanskarner.usecases;

import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class DomainErrorFilter {

    private final Map<Class<?>, Provider<DomainError>> mapError;

    @Inject
    public DomainErrorFilter(Map<Class<?>, Provider<DomainError>> mapError) {
        this.mapError = mapError;
    }

    public DomainError filter(Class<? extends DomainError> classKey) {
        return Objects.requireNonNull(mapError.get(classKey)).get();
    }

}