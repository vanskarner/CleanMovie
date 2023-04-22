package com.vanskarner.movie.domain.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.domain.TestFuturesUtils;
import com.vanskarner.movie.domain.repository.FakeLocalRepository;
import com.vanskarner.movie.domain.repository.MovieData;
import com.vanskarner.movie.domain.repository.MovieLocalRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeleteAllFavoriteMoviesUseCaseTest {
    TestFuturesUtils testFuturesUtils;
    DeleteAllFavoriteMoviesUseCase useCase;

    @Before
    public void setUp() {
        testFuturesUtils = new TestFuturesUtils();
        MovieLocalRepository localRepository = new FakeLocalRepository(testFuturesUtils,
                MovieData.getData());

        useCase = new DeleteAllFavoriteMoviesUseCase(localRepository);
    }

    @After
    public void tearDown() {
        testFuturesUtils.clear();
    }

    @Test
    public void execute_totalItemsDeleted() {
        Integer actualValue = useCase.execute().get();

        assertEquals(Integer.valueOf(MovieData.getQuantity()), actualValue);
    }

}