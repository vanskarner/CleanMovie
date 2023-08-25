package com.vanskarner.cleanmovie.ui.movie.upcoming;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vanskarner.cleanmovie.ui.errors.ViewErrorFilter;
import com.vanskarner.cleanmovie.ui.movie.MovieModel;
import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.TestFutureResult;
import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class UpcomingPresenterTest {

    static UpcomingContract.view view;
    static MovieServices services;
    static ViewErrorFilter errorFilter;
    static UpcomingPresenter presenter;

    @BeforeClass
    public static void setupClass() {
        view = mock(UpcomingContract.view.class);
        services = mock(MovieServices.class);
        errorFilter = mock(ViewErrorFilter.class);
        List<MovieModel> upcomingList = new ArrayList<>();
        List<MovieModel> fullUpcomingList = new ArrayList<>();

        presenter = new UpcomingPresenter(
                view,
                services,
                upcomingList,
                fullUpcomingList,
                errorFilter);
    }

    @After
    public void tearDown() {
        Mockito.clearInvocations(view, services, errorFilter);
    }

    @Test
    public void initialLoad_whenOK_doSuccessFlow() {
        int page = 1;
        List<MovieDS> list = new ArrayList<>();
        MovieDS item = new MovieDS(1, "", "");
        list.add(item);
        MoviesDS moviesDS = new MoviesDS(list);
        FutureResult<MoviesDS> futureResult = new TestFutureResult<>(moviesDS);
        when(services.showUpcoming(page)).thenReturn(futureResult);
        presenter.initialLoad(page);

        verify(view).enableScroll();
        verify(view,times(2)).setSearchView(anyBoolean());
        verify(view, times(2)).setInitialProgress(anyBoolean());
        verify(view).showUpcoming(anyList());
        verify(view).paginate();
    }

    @Test
    public void initialLoad_whenFail_doErrorFlow() {
        int page = 1;
        Exception anyException = new Exception("Any Exception");
        FutureResult<MoviesDS> futureResult = new TestFutureResult<>(anyException);
        when(services.showUpcoming(page)).thenReturn(futureResult);
        presenter.initialLoad(page);

        verify(view).enableScroll();
        verify(view).setSearchView(false);
        verify(view, times(2)).setInitialProgress(anyBoolean());
        verify(view).showError(any());
        verify(errorFilter).filter(anyException);
    }

    @Test
    public void initialLoad_whenPageIsNotOne_justEnableScroll() {
        int page = 2;
        presenter.initialLoad(page);

        verify(view).enableScroll();
    }

    @Test
    public void loadMoreItems_whenOK_doSuccessFlow() {
        int page = 2;
        List<MovieDS> list = new ArrayList<>();
        MovieDS item = new MovieDS(1, "", "");
        list.add(item);
        MoviesDS moviesDS = new MoviesDS(list);
        FutureResult<MoviesDS> futureResult = new TestFutureResult<>(moviesDS);
        when(services.showUpcoming(page)).thenReturn(futureResult);
        presenter.loadMoreItems(page, true);

        verify(view).setPagingProgress(true);
        verify(view).showUpcoming(anyList());
        verify(view).paginate();
    }

    @Test
    public void loadMoreItems_whenFail_doFailFlow() {
        int page = 2;
        Exception anyException = new Exception("Any Exception");
        FutureResult<MoviesDS> futureResult = new TestFutureResult<>(anyException);
        when(services.showUpcoming(page)).thenReturn(futureResult);
        presenter.loadMoreItems(page, true);


        verify(view, times(2)).setPagingProgress(anyBoolean());
        verify(view).enableScroll();
        verify(view).showError(any());
        verify(errorFilter).filter(anyException);
    }

    @Test
    public void async_doFlow() {
        presenter.asyncCancel();

        verify(services).clear();
        verify(view).setPagingProgress(false);
    }


}