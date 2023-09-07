package com.vanskarner.movie.presentation;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewErrorFilter {

    private final Map<Class<?>, Provider<ErrorView<?>>> mapError;
    private final ErrorView<?> defaultError;

    @Inject
    public ViewErrorFilter(Map<Class<?>, Provider<ErrorView<?>>> mapError,
                           ErrorView<?> defaultError) {
        this.mapError = mapError;
        this.defaultError = defaultError;
    }

    public ErrorView<?> filter(Throwable throwable) {
        Provider<ErrorView<?>> providerError = mapError.get(throwable.getClass());
        return providerError != null ? providerError.get() : defaultError;
    }

}