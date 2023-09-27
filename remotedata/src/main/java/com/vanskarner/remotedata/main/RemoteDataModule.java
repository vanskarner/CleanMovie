package com.vanskarner.remotedata.main;

import com.vanskarner.remotedata.movie.MovieRemoteModule;

import dagger.Module;

@Module(includes = {
        RemoteErrorsModule.class,
        MovieRemoteModule.class
})
public abstract class RemoteDataModule {
}