package com.vanskarner.cleanmovie.ui.errors.custom;

import com.vanskarner.cleanmovie.ui.errors.ErrorView;
import com.vanskarner.remotedata.RemoteError;
import com.vanskarner.usecases.movie.error.MovieFavoriteLimit;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/** @noinspection unused*/
@Module
public abstract class ViewErrorTypesModule {

    @Binds
    abstract ErrorView<?> bindDefaultViewError(UnknownError unknownError);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.NotFound.class)
    abstract ErrorView<?> bindNotFound(NotFoundError notFoundError);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.ServiceUnavailable.class)
    abstract ErrorView<?> bindServiceUnavailable(ServiceUnavailableError serviceUnavailableError);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.NoInternet.class)
    abstract ErrorView<?> bindNoInternet(NoInternetError noInternetError);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.Unauthorised.class)
    abstract ErrorView<?> bindUnauthorised(UnauthorisedError unauthorisedError);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.Server.class)
    abstract ErrorView<?> bindServer(ServerError serverError);

    @Binds
    @IntoMap
    @ClassKey(MovieFavoriteLimit.class)
    abstract ErrorView<?> bindFavoritesLimit(FavoritesLimitError favoritesLimitError);

}