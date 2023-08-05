package com.vanskarner.movie.persistence.remote;

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