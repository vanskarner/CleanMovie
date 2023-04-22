package com.vanskarner.movie.domain.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.domain.TestFuturesUtils;
import com.vanskarner.movie.domain.ds.MovieDetailDS;
import com.vanskarner.movie.domain.error.MovieError;
import com.vanskarner.movie.domain.repository.FakeLocalRepository;
import com.vanskarner.movie.domain.repository.MovieData;
import com.vanskarner.movie.domain.repository.MovieLocalRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActionFavoriteMovieUseCaseTest {
    TestFuturesUtils testFuturesUtils;
    ActionFavoriteMovieUseCase useCaseWithLocalCapacityExceeded;
    ActionFavoriteMovieUseCase useCaseWithLocalCapacityNotExceeded;
    MovieDetailDS registeredItem = MovieMapper.convert(MovieData.getData().get(0));
    MovieDetailDS unregisteredItem = MovieMapper.convert(MovieData.getData().get(3));

    @Before
    public void setUp() {
        testFuturesUtils = new TestFuturesUtils();
        MovieLocalRepository localRepositoryExceeded =
                new FakeLocalRepository(testFuturesUtils, MovieData.getData().subList(0, 2));
        MovieLocalRepository localRepositoryNotExceeded =
                new FakeLocalRepository(testFuturesUtils, MovieData.getData().subList(0, 1));
        MovieErrorFilter domainErrorFilter = new MockMovieErrorFilter();

        useCaseWithLocalCapacityExceeded = new ActionFavoriteMovieUseCase(localRepositoryExceeded,
                domainErrorFilter);
        useCaseWithLocalCapacityNotExceeded = new ActionFavoriteMovieUseCase(localRepositoryNotExceeded,
                domainErrorFilter);
    }

    @After
    public void tearDown() {
        testFuturesUtils.clear();
    }

    @Test
    public void executeWithLocalCapacityExceeded_registeredItem_returnFalse() {
        boolean actualValue = useCaseWithLocalCapacityExceeded
                .execute(registeredItem)
                .get();

        assertFalse(actualValue);
    }

    @Test(expected = MovieError.FavoriteLimit.class)
    public void executeWithLocalCapacityExceeded_itemNotRegistered_FavoriteMovieLimit() {
        useCaseWithLocalCapacityExceeded.execute(unregisteredItem).get();
    }

    @Test
    public void executeWithLocalCapacityNotExceeded_registeredItem_returnFalse() {
        boolean actualValue = useCaseWithLocalCapacityNotExceeded
                .execute(registeredItem)
                .get();

        assertFalse(actualValue);
    }

    @Test
    public void executeWithLocalCapacityNotExceeded_itemNotRegistered_returnTrue() {
        boolean actualValue = useCaseWithLocalCapacityNotExceeded
                .execute(unregisteredItem)
                .get();

        assertTrue(actualValue);
    }

}