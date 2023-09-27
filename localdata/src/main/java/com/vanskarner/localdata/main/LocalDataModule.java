package com.vanskarner.localdata.main;

import com.vanskarner.localdata.movie.MovieLocalModule;

import dagger.Module;

/**
 * @noinspection unused
 */
@Module(includes = {
        MovieLocalModule.class
})
public abstract class LocalDataModule {
}