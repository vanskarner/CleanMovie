package com.vanskarner.cleanmovie.ui.movie.favorites;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.vanskarner.cleanmovie.ui.errors.ViewErrorFilter;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.TestFutureResult;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

public class FavoritesPresenterTest {
    static FavoritesContract.view view;
    static MovieServices movieServices;
    static ViewErrorFilter viewErrorFilter;
    static FavoritesPresenter presenter;

    @BeforeClass
    public static void setup() {
        view = Mockito.mock(FavoritesContract.view.class);
        movieServices = Mockito.mock(MovieServices.class);
        viewErrorFilter = Mockito.mock(ViewErrorFilter.class);
        presenter = new FavoritesPresenter(view, movieServices, viewErrorFilter);
    }

    @Test
    public void getFavorites_whenFail_doErrorFlow() {
        FutureResult<MoviesDS> result = new TestFutureResult<>(new Exception("Any Exception"));
        Mockito.when(movieServices.showFavorite())
                .thenReturn(result);
        presenter.getFavorites();

        verify(view, atLeastOnce()).showError(Mockito.any());
        verify(viewErrorFilter, atLeastOnce()).filter(Mockito.any());
    }

    @Test
    public void getFavorites_whenOk_doSuccessFlow() {
        FutureResult<MoviesDS> result =
                new TestFutureResult<>(new MoviesDS(Collections.emptyList()));
        Mockito.when(movieServices.showFavorite())
                .thenReturn(result);
        presenter.getFavorites();

        verify(view, atLeastOnce()).showFavorites(anyList());
        verify(view, atLeastOnce()).setNotFavorites(anyBoolean());
    }

    @Test
    public void getFavoriteDetail_whenFail_doErrorFlow() {
        FutureResult<MovieDetailDS> result = new TestFutureResult<>(new Exception("Any Exception"));
        Mockito.when(movieServices.findFavorite(anyInt()))
                .thenReturn(result);
        presenter.getFavoriteDetail(anyInt());

        verify(viewErrorFilter, atLeastOnce()).filter(Mockito.any());
        verify(view, atLeastOnce()).showError(Mockito.any());
    }

    @Test
    public void getFavoriteDetail_whenOk_doSuccessFlow() {
        FutureResult<MovieDetailDS> result =
                new TestFutureResult<>(Mockito.mock(MovieDetailDS.class));
        Mockito.when(movieServices.findFavorite(anyInt()))
                .thenReturn(result);
        presenter.getFavoriteDetail(anyInt());

        verify(view, atLeastOnce()).showFavoriteDetail(Mockito.any());
    }

    @Test
    public void deleteFavorites_whenFail_doErrorFlow() {
        FutureResult<Integer> result = new TestFutureResult<>(new Exception("Any Exception"));
        Mockito.when(movieServices.deleteAllFavorite())
                .thenReturn(result);
        presenter.deleteFavorites();

        verify(viewErrorFilter, atLeastOnce()).filter(Mockito.any());
        verify(view, atLeastOnce()).showError(Mockito.any());
    }

    @Test
    public void deleteFavorites_whenOk_doSuccessFlow() {
        FutureResult<Integer> result = new TestFutureResult<>(1);
        Mockito.when(movieServices.deleteAllFavorite())
                .thenReturn(result);
        presenter.deleteFavorites();

        verify(view, atLeastOnce()).showFavorites(anyList());
        verify(view, atLeastOnce()).setNotFavorites(anyBoolean());
        verify(view, atLeastOnce()).showRemovedItems(anyInt());
    }

    @Test
    public void asyncCancel() {
        presenter.asyncCancel();

        verify(movieServices, atLeastOnce()).clear();
    }

}