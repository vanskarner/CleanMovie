package com.vanskarner.movie.persistence.remote;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

/** @noinspection unused*/
@Module
abstract class MovieRemoteErrorsModule {

    @Binds
    abstract MovieRemoteError bindDefaultRemoteError(MovieRemoteError.Server error);

    @Binds
    @IntoMap
    @StringKey("NoInternet")
    abstract MovieRemoteError bindNoInternet(MovieRemoteError.NoInternet error);

    @Binds
    @IntoMap
    @StringKey("401")
    abstract MovieRemoteError bindUnauthorised(MovieRemoteError.Unauthorised error);

    @Binds
    @IntoMap
    @StringKey("404")
    abstract MovieRemoteError bindNotFound(MovieRemoteError.NotFound error);

    @Binds
    @IntoMap
    @StringKey("503")
    abstract MovieRemoteError bindServiceUnavailable(MovieRemoteError.ServiceUnavailable error);

}