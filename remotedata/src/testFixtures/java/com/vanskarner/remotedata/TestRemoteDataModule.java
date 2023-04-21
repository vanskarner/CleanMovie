package com.vanskarner.remotedata;

import dagger.Module;

@Module(includes = {
        RemoteErrorsModule.class,
        TestMovieRemoteModule.class
})
public class TestRemoteDataModule {
}