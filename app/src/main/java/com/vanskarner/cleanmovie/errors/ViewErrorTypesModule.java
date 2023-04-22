package com.vanskarner.cleanmovie.errors;

import com.vanskarner.cleanmovie.errors.types.ErrorView;
import com.vanskarner.cleanmovie.errors.types.FavoritesLimitError;
import com.vanskarner.cleanmovie.errors.types.NoInternetError;
import com.vanskarner.cleanmovie.errors.types.NotFoundError;
import com.vanskarner.cleanmovie.errors.types.ServerError;
import com.vanskarner.cleanmovie.errors.types.ServiceUnavailableError;
import com.vanskarner.cleanmovie.errors.types.UnauthorisedError;
import com.vanskarner.cleanmovie.errors.types.UnknownError;
import com.vanskarner.movie.data.remote.MovieRemoteError;
import com.vanskarner.movie.domain.error.MovieError;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewErrorTypesModule {

    @Binds
    abstract ErrorView<?> bindDefaultViewError(UnknownError unknownError);

    @Binds
    @IntoMap
    @ClassKey(MovieRemoteError.NotFound.class)
    abstract ErrorView<?> bindNotFound(NotFoundError notFoundError);

    @Binds
    @IntoMap
    @ClassKey(MovieRemoteError.ServiceUnavailable.class)
    abstract ErrorView<?> bindServiceUnavailable(ServiceUnavailableError serviceUnavailableError);

    @Binds
    @IntoMap
    @ClassKey(MovieRemoteError.NoInternet.class)
    abstract ErrorView<?> bindNoInternet(NoInternetError noInternetError);

    @Binds
    @IntoMap
    @ClassKey(MovieRemoteError.Unauthorised.class)
    abstract ErrorView<?> bindUnauthorised(UnauthorisedError unauthorisedError);

    @Binds
    @IntoMap
    @ClassKey(MovieRemoteError.Server.class)
    abstract ErrorView<?> bindServer(ServerError serverError);

    @Binds
    @IntoMap
    @ClassKey(MovieError.FavoriteLimit.class)
    abstract ErrorView<?> bindFavoritesLimit(FavoritesLimitError favoritesLimitError);

}