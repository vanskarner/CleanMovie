package com.vanskarner.movie.persistence.remote;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
class RemoteDataErrorFilter {

    private final Map<String, Provider<MovieRemoteError>> mapError;
    private final MovieRemoteError defaultError;

    @Inject
    public RemoteDataErrorFilter(Map<String, Provider<MovieRemoteError>> mapError,
                                 MovieRemoteError defaultError) {
        this.mapError = mapError;
        this.defaultError = defaultError;
    }

    public MovieRemoteError filter(String key) {
        Provider<MovieRemoteError> provider = mapError.get(String.valueOf(key));
        throw provider != null ? provider.get() : defaultError;
    }

    public MovieRemoteError getNoInternetError() {
        return filter("NoInternet");
    }

}