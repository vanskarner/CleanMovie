package com.vanskarner.usecases.movie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.usecases.TestFuturesUtils;
import com.vanskarner.usecases.movie.repository.FakeLocalRepository;
import com.vanskarner.usecases.movie.repository.MovieData;
import com.vanskarner.usecases.movie.repository.MovieLocalRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckFavoriteMovieUseCaseTest {
    TestFuturesUtils testFuturesUtils;
    CheckFavoriteMovieUseCase useCase;

    @Before
    public void setUp() {
        testFuturesUtils = new TestFuturesUtils();
        MovieLocalRepository localRepository = new FakeLocalRepository(testFuturesUtils,
                MovieData.getData());

        useCase = new CheckFavoriteMovieUseCase(localRepository);
    }

    @After
    public void tearDown() {
        testFuturesUtils.clear();
    }

    @Test
    public void execute_validID_returnTrue() {
        boolean actualValue = useCase
                .execute(MovieData.getAnyItem().getId())
                .get();

        assertTrue(actualValue);
    }

    @Test
    public void execute_invalidID_returnFalse() {
        boolean actualValue = useCase
                .execute(MovieData.getInvalidID())
                .get();

        assertFalse(actualValue);
    }

}