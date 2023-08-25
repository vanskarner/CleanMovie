package com.vanskarner.cleanmovie.ui.movie.favorites;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
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
    public static void setup() {
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
    public void getFavorites_whenFail_doErrorFlow() {
        FutureResult<MoviesDS> result = new TestFutureResult<>(new Exception("Any Exception"));
        when(services.showFavorite())
                .thenReturn(result);
        presenter.getFavorites();

        verify(view).showError(any());
        verify(errorFilter).filter(any());
    }

    @Test
    public void getFavorites_whenOk_doSuccessFlow() {
        FutureResult<MoviesDS> result =
                new TestFutureResult<>(new MoviesDS(Collections.emptyList()));
        when(services.showFavorite())
                .thenReturn(result);
        presenter.getFavorites();

        verify(view).showFavorites(anyList());
        verify(view).setNotFavorites(anyBoolean());
    }

    @Test
    public void getFavoriteDetail_whenFail_doErrorFlow() {
        FutureResult<MovieDetailDS> result = new TestFutureResult<>(new Exception("Any Exception"));
        when(services.findFavorite(anyInt()))
                .thenReturn(result);
        presenter.getFavoriteDetail(anyInt());

        verify(errorFilter).filter(any());
        verify(view).showError(any());
    }

    @Test
    public void getFavoriteDetail_whenOk_doSuccessFlow() {
        FutureResult<MovieDetailDS> result =
                new TestFutureResult<>(mock(MovieDetailDS.class));
        when(services.findFavorite(anyInt()))
                .thenReturn(result);
        presenter.getFavoriteDetail(anyInt());

        verify(view).showFavoriteDetail(any());
    }

    @Test
    public void deleteFavorites_whenFail_doErrorFlow() {
        FutureResult<Integer> result = new TestFutureResult<>(new Exception("Any Exception"));
        when(services.deleteAllFavorite())
                .thenReturn(result);
        presenter.deleteFavorites();

        verify(errorFilter).filter(any());
        verify(view).showError(any());
    }

    @Test
    public void deleteFavorites_whenOk_doSuccessFlow() {
        FutureResult<Integer> result = new TestFutureResult<>(1);
        when(services.deleteAllFavorite())
                .thenReturn(result);
        presenter.deleteFavorites();

        verify(view).showFavorites(anyList());
        verify(view).setNotFavorites(anyBoolean());
        verify(view).showRemovedItems(anyInt());
    }

    @Test
    public void asyncCancel_doFlow() {
        presenter.asyncCancel();

        verify(services).clear();
    }

}