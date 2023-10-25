package com.vanskarner.remotedata.movie;

import com.vanskarner.remotedata.RemoteError;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
class RemoteDataErrorFilter {

    private final Map<String, Provider<RemoteError>> mapError;
    private final RemoteError defaultError;

    @Inject
    public RemoteDataErrorFilter(Map<String, Provider<RemoteError>> mapError,
                                 RemoteError defaultError) {
        this.mapError = mapError;
        this.defaultError = defaultError;
    }

    public RemoteError filter(String key) {
        Provider<RemoteError> provider = mapError.get(key);
        throw provider == null ? defaultError : provider.get();
    }

    public RemoteError getNoInternetError() {
        return filter("NoInternet");
    }

}