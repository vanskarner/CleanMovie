package com.vanskarner.movie.domain.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.domain.TestFuturesUtils;
import com.vanskarner.movie.domain.ds.MoviesDS;
import com.vanskarner.movie.domain.repository.FakeRemoteRepository;
import com.vanskarner.movie.domain.repository.MovieData;
import com.vanskarner.movie.domain.repository.MovieRemoteRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShowUpcomingMoviesUseCaseTest {
    TestFuturesUtils testFuturesUtils;
    ShowUpcomingMoviesUseCase useCase;

    @Before
    public void setUp() {
        testFuturesUtils = new TestFuturesUtils();
        MovieRemoteRepository remoteRepository = new FakeRemoteRepository(testFuturesUtils,
                MovieData.getData());

        useCase = new ShowUpcomingMoviesUseCase(remoteRepository);
    }

    @After
    public void tearDown() {
        testFuturesUtils.clear();
    }

    @Test
    public void execute_returnItems() {
        int expectedQuantity = MovieData.getQuantity();
        MoviesDS moviesDS = useCase.execute(1).get();
        int actualQuantity = moviesDS.list.size();

        assertEquals(expectedQuantity, actualQuantity);
    }

}