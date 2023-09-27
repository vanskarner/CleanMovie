package com.vanskarner.remotedata.main;

import com.vanskarner.remotedata.movie.TestMovieRemoteModule;

import dagger.Module;

@Module(includes = {
        RemoteErrorsModule.class,
        TestMovieRemoteModule.class
})
public class TestRemoteDataModule {
}