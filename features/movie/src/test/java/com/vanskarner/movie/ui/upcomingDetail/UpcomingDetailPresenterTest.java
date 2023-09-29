package com.vanskarner.movie.ui.upcomingDetail;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.TestFutureFactory;
import com.vanskarner.movie.ui.MovieDetailDS;
import com.vanskarner.movie.businesslogic.MovieServices;
import com.vanskarner.movie.ui.ViewErrorFilter;

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
    public void initialAction_whenItsOk_doOkSequence() {
        int movieId = 1010;
        boolean checkResult = true;
        MovieDetailDS item = MovieDetailDS.empty();
        FutureResult<Boolean> checkFuture = TestFutureFactory.create(checkResult);
        FutureResult<MovieDetailDS> findFuture = TestFutureFactory.create(item);
        when(services.checkFavorite(movieId)).thenReturn(checkFuture);
        when(services.findUpcoming(movieId)).thenReturn(findFuture);
        presenter.initialAction(movieId);

        verify(view).setReadyViews(false);
        verify(view).setMarkedAsFavorite(checkResult);
        verify(view).setReadyViews(true);
        verify(view).showUpcomingDetail(any());
    }

    @Test
    public void initialAction_whenItFails_doFailSequence() {
        int movieId = 1010;
        Exception anyException = new Exception("Any Exception");
        FutureResult<Boolean> checkFuture = TestFutureFactory.create(anyException);
        FutureResult<MovieDetailDS> findFuture = TestFutureFactory.create(anyException);
        when(services.checkFavorite(movieId)).thenReturn(checkFuture);
        when(services.findUpcoming(movieId)).thenReturn(findFuture);
        presenter.initialAction(movieId);

        verify(view, times(2)).showError(any());
        verify(errorFilter, times(2)).filter(anyException);
    }

    @Test
    public void actionFavoriteMovie_whenItsOk_doOkSequence() {
        boolean result = true;
        MovieDetailDS item = MovieDetailDS.empty();
        FutureResult<Boolean> futureResult = TestFutureFactory.create(result);
        when(services.toggleFavorite(any())).thenReturn(futureResult);
        presenter.actionFavoriteMovie(item);

        verify(view).setMarkedAsFavorite(result);
    }

    @Test
    public void actionFavoriteMovie_whenItFails_doFailSequence() {
        MovieDetailDS item = MovieDetailDS.empty();
        Exception anyException = new Exception("Any Exception");
        FutureResult<Boolean> futureResult = TestFutureFactory.create(anyException);
        when(services.toggleFavorite(any())).thenReturn(futureResult);
        presenter.actionFavoriteMovie(item);

        verify(view).showError(any());
        verify(errorFilter).filter(anyException);
    }

    @Test
    public void asyncCancel_doSequence() {
        presenter.asyncCancel();

        verify(services).clear();
    }

}