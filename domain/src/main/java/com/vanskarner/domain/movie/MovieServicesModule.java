package com.vanskarner.domain.movie;

import com.vanskarner.domain.DomainError;
import com.vanskarner.domain.movie.service.MovieFavoriteLimit;
import com.vanskarner.domain.movie.service.MovieServices;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/** @noinspection unused*/
@Module
public abstract class MovieServicesModule {

    @Binds
    @Singleton
    public abstract MovieServices bindMovieServices(MovieDefaultServices services);

    @Binds
    @IntoMap
    @ClassKey(MovieFavoriteLimit.class)
    public abstract DomainError provideFavoriteMovieLimit(MovieFavoriteLimit error);

}