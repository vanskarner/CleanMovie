package com.vanskarner.cleanmovie.ui.errors.custom;

import com.vanskarner.cleanmovie.ui.errors.ErrorView;
import com.vanskarner.remotedata.RemoteError;
import com.vanskarner.domain.movie.service.MovieFavoriteLimit;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/** @noinspection unused*/
@Module
public abstract class ViewErrorTypesModule {

    @Binds
    public abstract ErrorView<?> bindDefaultViewError(UnknownError error);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.NotFound.class)
    public abstract ErrorView<?> bindNotFound(NotFoundError error);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.ServiceUnavailable.class)
    public abstract ErrorView<?> bindServiceUnavailable(ServiceUnavailableError error);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.NoInternet.class)
    public abstract ErrorView<?> bindNoInternet(NoInternetError error);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.Unauthorised.class)
    public abstract ErrorView<?> bindUnauthorised(UnauthorisedError error);

    @Binds
    @IntoMap
    @ClassKey(RemoteError.Server.class)
    public abstract ErrorView<?> bindServer(ServerError error);

    @Binds
    @IntoMap
    @ClassKey(MovieFavoriteLimit.class)
    public abstract ErrorView<?> bindFavoritesLimit(FavoritesLimitError error);

}