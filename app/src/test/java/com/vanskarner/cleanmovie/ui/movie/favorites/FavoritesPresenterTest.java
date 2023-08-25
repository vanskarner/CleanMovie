package com.vanskarner.cleanmovie.ui.movie.favorites;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vanskarner.cleanmovie.ui.errors.ViewErrorFilter;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.TestFutureResult;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

public class FavoritesPresenterTest {
    static FavoritesContract.view view;
    static MovieServices services;
    static ViewErrorFilter errorFilter;
    static FavoritesPresenter presenter;

    @BeforeClass
    public static void setupClass() {
        view = mock(FavoritesContract.view.class);
        services = mock(MovieServices.class);
        errorFilter = mock(ViewErrorFilter.class);
        presenter = new FavoritesPresenter(view, services, errorFilter);
    }

    @After
    public void tearDown() {
        Mockito.clearInvocations(view, services, errorFilter);
    }

    @Test
    public void getFavorites_whenItFails_doFailSequence() {
        Exception anyException = new Exception("Any Exception");
        FutureResult<MoviesDS> futureResult = new TestFutureResult<>(anyException);
        when(services.showFavorite()).thenReturn(futureResult);
        presenter.getFavorites();

        verify(view).showError(any());
        verify(errorFilter).filter(anyException);
    }

    @Test
    public void getFavorites_whenItsOK_doOkSequence() {
        MoviesDS moviesDS = new MoviesDS(Collections.emptyList());
        FutureResult<MoviesDS> futureResult = new TestFutureResult<>(moviesDS);
        when(services.showFavorite()).thenReturn(futureResult);
        presenter.getFavorites();

        verify(view).showFavorites(anyList());
        verify(view).setNotFavorites(anyBoolean());
    }

    @Test
    public void getFavoriteDetail_whenItFails_doFailSequence() {
        int movieId = 1;
        Exception anyException = new Exception("Any Exception");
        FutureResult<MovieDetailDS> futureResult = new TestFutureResult<>(anyException);
        when(services.findFavorite(movieId)).thenReturn(futureResult);
        presenter.getFavoriteDetail(movieId);

        verify(view).showError(any());
        verify(errorFilter).filter(anyException);
    }

    @Test
    public void getFavoriteDetail_whenItsOK_doOkSequence() {
        int movieId = 1;
        MovieDetailDS item = mock(MovieDetailDS.class);
        FutureResult<MovieDetailDS> futureResult = new TestFutureResult<>(item);
        when(services.findFavorite(movieId)).thenReturn(futureResult);
        presenter.getFavoriteDetail(movieId);

        verify(view).showFavoriteDetail(any());
    }

    @Test
    public void deleteFavorites_whenItFails_doFailSequence() {
        Exception anyException = new Exception("Any Exception");
        FutureResult<Integer> futureResult = new TestFutureResult<>(anyException);
        when(services.deleteAllFavorite()).thenReturn(futureResult);
        presenter.deleteFavorites();

        verify(view).showError(any());
        verify(errorFilter).filter(anyException);
    }

    @Test
    public void deleteFavorites_whenItsOK_doOkSequence() {
        int itemsRemoved = 1;
        FutureResult<Integer> futureResult = new TestFutureResult<>(itemsRemoved);
        when(services.deleteAllFavorite()).thenReturn(futureResult);
        presenter.deleteFavorites();

        verify(view).showFavorites(anyList());
        verify(view).setNotFavorites(true);
        verify(view).showRemovedItems(itemsRemoved);
    }

    @Test
    public void asyncCancel_doFlow() {
        presenter.asyncCancel();

        verify(services).clear();
    }

}