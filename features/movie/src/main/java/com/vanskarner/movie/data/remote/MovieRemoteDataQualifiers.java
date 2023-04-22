package com.vanskarner.movie.data.remote;

import javax.inject.Qualifier;

public class MovieRemoteDataQualifiers {
    @Qualifier
    public @interface MovieImageUrl {
    }

    @Qualifier
    public @interface MovieUrl {
    }

    @Qualifier
    public @interface Apikey {
    }
}