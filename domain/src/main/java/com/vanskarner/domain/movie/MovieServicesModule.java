package com.vanskarner.domain.movie;

import com.vanskarner.domain.common.UseCase;

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
    @ClassKey(MovieError.FavoriteLimitError.class)
    public abstract MovieError provideFavoriteMovieLimit(MovieError.FavoriteLimitError error);

    @Binds
    @IntoMap
    @ClassKey(CheckFavoriteMovieUseCase.class)
    public abstract UseCase<?,?> bindCheckFavoriteMovieUseCase(CheckFavoriteMovieUseCase useCase);

    @Binds
    @IntoMap
    @ClassKey(DeleteAllFavoriteMoviesUseCase.class)
    public abstract UseCase<?,?>
    bindDeleteAllFavoriteMoviesUseCase(DeleteAllFavoriteMoviesUseCase useCase);

    @Binds
    @IntoMap
    @ClassKey(FilterUpcomingMoviesUseCase.class)
    public abstract UseCase<?,?>
    bindFilterUpcomingMoviesUseCase(FilterUpcomingMoviesUseCase useCase);

    @Binds
    @IntoMap
    @ClassKey(FindFavoriteMovieUseCase.class)
    public abstract UseCase<?,?>
    bindFindFavoriteMovieUseCase(FindFavoriteMovieUseCase useCase);

    @Binds
    @IntoMap
    @ClassKey(FindUpcomingMovieUseCase.class)
    public abstract UseCase<?,?>
    bindFindUpcomingMovieUseCase(FindUpcomingMovieUseCase useCase);

    @Binds
    @IntoMap
    @ClassKey(ShowFavoriteMoviesUseCase.class)
    public abstract UseCase<?,?>
    bindShowFavoriteMoviesUseCase(ShowFavoriteMoviesUseCase useCase);

    @Binds
    @IntoMap
    @ClassKey(ShowUpcomingMoviesUseCase.class)
    public abstract UseCase<?,?>
    bindShowUpcomingMoviesUseCase(ShowUpcomingMoviesUseCase useCase);

    @Binds
    @IntoMap
    @ClassKey(ToggleMovieFavoriteUseCase.class)
    public abstract UseCase<?,?>
    bindToggleMovieFavoriteUseCase(ToggleMovieFavoriteUseCase useCase);

}