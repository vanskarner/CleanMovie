package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.TestFuturesUtils;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.repository.FakeLocalRepository;
import com.vanskarner.movie.businesslogic.repository.MovieData;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

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