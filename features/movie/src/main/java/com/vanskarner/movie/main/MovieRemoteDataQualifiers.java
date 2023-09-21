package com.vanskarner.movie.main;

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