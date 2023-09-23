package com.vanskarner.cleanmovie.ui.errors;

import com.vanskarner.movie.persistence.remote.MovieRemoteError;
import com.vanskarner.movie.businesslogic.MovieError;

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
    @ClassKey(MovieRemoteError.NotFound.class)
    public abstract ErrorView<?> bindNotFound(NotFoundError error);

    @Binds
    @IntoMap
    @ClassKey(MovieRemoteError.ServiceUnavailable.class)
    public abstract ErrorView<?> bindServiceUnavailable(ServiceUnavailableError error);

    @Binds
    @IntoMap
    @ClassKey(MovieRemoteError.NoInternet.class)
    public abstract ErrorView<?> bindNoInternet(NoInternetError error);

    @Binds
    @IntoMap
    @ClassKey(MovieRemoteError.Unauthorised.class)
    public abstract ErrorView<?> bindUnauthorised(UnauthorisedError error);

    @Binds
    @IntoMap
    @ClassKey(MovieRemoteError.Server.class)
    public abstract ErrorView<?> bindServer(ServerError error);

    @Binds
    @IntoMap
    @ClassKey(MovieError.FavoriteLimit.class)
    public abstract ErrorView<?> bindFavoritesLimit(FavoritesLimitError error);

}