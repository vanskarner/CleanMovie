package com.vanskarner.remotedata;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
abstract class RemoteErrorsModule {

    @Binds
    abstract RemoteError bindDefaultRemoteError(RemoteError.Server error);

    @Binds
    @IntoMap
    @StringKey("NoInternet")
    abstract RemoteError bindNoInternet(RemoteError.NoInternet error);

    @Binds
    @IntoMap
    @StringKey("401")
    abstract RemoteError bindUnauthorised(RemoteError.Unauthorised error);

    @Binds
    @IntoMap
    @StringKey("404")
    abstract RemoteError bindNotFound(RemoteError.NotFound error);

    @Binds
    @IntoMap
    @StringKey("500")
    abstract RemoteError bindServer(RemoteError.Server error);

    @Binds
    @IntoMap
    @StringKey("503")
    abstract RemoteError bindServiceUnavailable(RemoteError.ServiceUnavailable error);

}