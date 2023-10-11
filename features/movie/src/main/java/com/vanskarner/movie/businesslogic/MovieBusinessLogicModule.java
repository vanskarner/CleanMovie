package com.vanskarner.movie.businesslogic;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/** @noinspection unused*/
@Module
public abstract class MovieBusinessLogicModule {

    @Binds
    @Singleton
    abstract MovieServices bindMovieServices(MovieDefaultServices movieDefaultServices);

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