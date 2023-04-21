package com.vanskarner.remotedata;

import dagger.Module;

@Module(includes = {
        RemoteErrorsModule.class,
        MovieRemoteModule.class
})
public abstract class RemoteDataModule {
}