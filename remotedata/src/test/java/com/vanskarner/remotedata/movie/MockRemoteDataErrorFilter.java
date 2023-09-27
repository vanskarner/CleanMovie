package com.vanskarner.remotedata.movie;

import com.vanskarner.remotedata.RemoteError;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

class MockRemoteDataErrorFilter extends RemoteDataErrorFilter {

    public MockRemoteDataErrorFilter() {
        super(getMapError(), new RemoteError.Server());
    }

    private static Map<String, Provider<RemoteError>> getMapError() {
        Map<String, Provider<RemoteError>> map = new HashMap<>();
        map.put("NoInternet", RemoteError.NoInternet::new);
        map.put("401", RemoteError.Unauthorised::new);
        map.put("404", RemoteError.NotFound::new);
        map.put("503", RemoteError.ServiceUnavailable::new);
        return map;
    }

}