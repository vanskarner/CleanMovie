package com.vanskarner.usecases.movie;

import com.vanskarner.usecases.TestFuturesUtils;
import com.vanskarner.usecases.movie.ds.MoviesDS;
import com.vanskarner.usecases.movie.repository.FakeLocalRepository;
import com.vanskarner.usecases.movie.repository.MovieData;
import com.vanskarner.usecases.movie.repository.MovieLocalRepository;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShowFavoriteMoviesUseCaseTest {
    TestFuturesUtils testFuturesUtils;
    ShowFavoriteMoviesUseCase useCase;

    @Before
    public void setUp() {
        testFuturesUtils = new TestFuturesUtils();
        MovieLocalRepository localRepository = new FakeLocalRepository(testFuturesUtils,
                MovieData.getData());

        useCase = new ShowFavoriteMoviesUseCase(localRepository);
    }

    @After
    public void tearDown() {
        testFuturesUtils.clear();
    }

    @Test
    public void execute_returnItems() {
        MoviesDS moviesDS = useCase.execute().get();
        int actualQuantity = moviesDS.list.size();

        assertEquals(MovieData.getQuantity(), actualQuantity);
    }

}