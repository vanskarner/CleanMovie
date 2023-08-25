package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vanskarner.cleanmovie.ui.errors.ViewErrorFilter;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.TestFutureResult;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class UpcomingDetailPresenterTest {
    static UpcomingDetailContract.view view;
    static MovieServices services;
    static ViewErrorFilter errorFilter;
    static UpcomingDetailPresenter presenter;

    @BeforeClass
    public static void setupClass() {
        view = mock(UpcomingDetailContract.view.class);
        services = mock(MovieServices.class);
        errorFilter = mock(ViewErrorFilter.class);

        presenter = new UpcomingDetailPresenter(view, services, errorFilter);
    }

    @After
    public void tearDown() {
        Mockito.clearInvocations(view, services, errorFilter);
    }

    @Test
    public void initialAction_whenOK_doSuccessFlow() {
        int movieId = 1010;
        boolean checkResult = true;
        MovieDetailDS item =
                new MovieDetailDS(1, "", "", "", 0, 0, "", "");
        FutureResult<Boolean> checkFuture = new TestFutureResult<>(checkResult);
        FutureResult<MovieDetailDS> findFuture = new TestFutureResult<>(item);
        when(services.checkFavorite(movieId)).thenReturn(checkFuture);
        when(services.findUpcoming(movieId)).thenReturn(findFuture);
        presenter.initialAction(movieId);

        verify(view).setReadyViews(false);
        verify(view).setMarkedAsFavorite(checkResult);
        verify(view).setReadyViews(true);
        verify(view).showUpcomingDetail(any());
    }

    @Test
    public void initialAction_whenFail_doFailFlow() {
        int movieId = 1010;
        Exception anyException = new Exception("Any Exception");
        FutureResult<Boolean> checkFuture = new TestFutureResult<>(anyException);
        FutureResult<MovieDetailDS> findFuture = new TestFutureResult<>(anyException);
        when(services.checkFavorite(movieId)).thenReturn(checkFuture);
        when(services.findUpcoming(movieId)).thenReturn(findFuture);
        presenter.initialAction(movieId);

        verify(view, times(2)).showError(any());
        verify(errorFilter, times(2)).filter(anyException);
    }

    @Test
    public void asyncCancel_doFlow() {
        presenter.asyncCancel();

        verify(services).clear();
    }

}