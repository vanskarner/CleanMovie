package com.vanskarner.movie.persistence.remote;

import com.vanskarner.movie.MovieRemoteError;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

class MockRemoteDataErrorFilter extends RemoteDataErrorFilter {

    public MockRemoteDataErrorFilter() {
        super(getMapError(), new MovieRemoteError.Server());
    }

    private static Map<String, Provider<MovieRemoteError>> getMapError() {
        Map<String, Provider<MovieRemoteError>> map = new HashMap<>();
        map.put("NoInternet", MovieRemoteError.NoInternet::new);
        map.put("401", MovieRemoteError.Unauthorised::new);
        map.put("404", MovieRemoteError.NotFound::new);
        map.put("503", MovieRemoteError.ServiceUnavailable::new);
        return map;
    }

}