package com.vanskarner.movie.main;

import com.vanskarner.movie.MovieRemoteError;
import com.vanskarner.movie.MovieError;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

/** @noinspection unused*/
@Module
public abstract class MovieErrorModule {

    @Binds
    @IntoMap
    @ClassKey(MovieError.FavoriteLimit.class)
    public abstract MovieError provideFavoriteMovieLimit(MovieError.FavoriteLimit error);

    @Binds
    public abstract MovieRemoteError bindDefaultRemoteError(MovieRemoteError.Server error);

    @Binds
    @IntoMap
    @StringKey("NoInternet")
    public abstract MovieRemoteError bindNoInternet(MovieRemoteError.NoInternet error);

    @Binds
    @IntoMap
    @StringKey("401")
    public abstract MovieRemoteError bindUnauthorised(MovieRemoteError.Unauthorised error);

    @Binds
    @IntoMap
    @StringKey("404")
    public abstract MovieRemoteError bindNotFound(MovieRemoteError.NotFound error);

    @Binds
    @IntoMap
    @StringKey("503")
    public abstract MovieRemoteError bindServiceUnavailable(MovieRemoteError.ServiceUnavailable error);

}