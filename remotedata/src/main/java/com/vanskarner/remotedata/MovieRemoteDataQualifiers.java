package com.vanskarner.remotedata;

import javax.inject.Qualifier;

public class MovieRemoteDataQualifiers {
    @Qualifier
    public @interface BaseImageUrl {
    }

    @Qualifier
    public @interface BaseUrl {
    }

    @Qualifier
    public @interface Apikey {
    }
}