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
        FutureResult<Boolean> check = new TestFutureResult<>(checkResult);
        FutureResult<MovieDetailDS> find = new TestFutureResult<>(mock(MovieDetailDS.class));
        when(services.checkFavorite(movieId))
                .thenReturn(check);
        when(services.findUpcoming(movieId))
                .thenReturn(find);
        presenter.initialAction(movieId);

        verify(view).setReadyViews(false);
        verify(view).setMarkedAsFavorite(checkResult);
        verify(view).setReadyViews(true);
        verify(view).showUpcomingDetail(any());
    }

    @Test
    public void initialAction_whenFail_doFailFlow() {
        int movieId = 1010;
        FutureResult<Boolean> check = new TestFutureResult<>(new Exception("Any Exception"));
        FutureResult<MovieDetailDS> find = new TestFutureResult<>(new Exception("Any Exception"));
        when(services.checkFavorite(movieId))
                .thenReturn(check);
        when(services.findUpcoming(movieId))
                .thenReturn(find);
        presenter.initialAction(movieId);

        verify(view, times(2)).showError(any());
        verify(errorFilter, times(2)).filter(any());
    }

    @Test
    public void asyncCancel_doFlow() {
        presenter.asyncCancel();

        verify(services).clear();
    }

}