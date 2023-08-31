package com.vanskarner.usecases.movie;

import com.vanskarner.entities.MovieBO;
import com.vanskarner.usecases.TestFuturesUtils;
import com.vanskarner.usecases.movie.ds.MovieDetailDS;
import com.vanskarner.usecases.movie.repository.FakeRemoteRepository;
import com.vanskarner.usecases.movie.repository.MovieData;
import com.vanskarner.usecases.movie.repository.MovieRemoteRepository;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class FindUpcomingMovieUseCaseTest {
    TestFuturesUtils testFuturesUtils;
    FindUpcomingMovieUseCase useCase;

    @Before
    public void setUp() {
        testFuturesUtils = new TestFuturesUtils();
        MovieRemoteRepository remoteRepository = new FakeRemoteRepository(testFuturesUtils,
                MovieData.getData());

        useCase = new FindUpcomingMovieUseCase(remoteRepository);
    }

    @After
    public void tearDown() {
        testFuturesUtils.clear();
    }

    @Test
    public void execute_validID_returnItem() {
        MovieBO expected = MovieData.getAnyItem();
        MovieDetailDS actual = useCase.execute(expected.getId()).get();

        assertEquals(expected.getId(), actual.id);
        assertEquals(expected.getTitle(), actual.title);
        assertEquals(expected.getImage(), actual.image);
        assertEquals(expected.getBackgroundImage(), actual.backgroundImage);
        assertEquals(expected.getVoteCount(), actual.voteCount);
        assertEquals(expected.getVoteAverage(), actual.voteAverage, 0.01);
        assertEquals(expected.getReleaseDate(), actual.releaseDate);
        assertEquals(expected.getOverview(), actual.overview);
        assertEquals(expected.isRecommended(), actual.recommended);
    }

    @Test(expected = NoSuchElementException.class)
    public void execute_invalidID_noSuchElementException() {
        useCase.execute(MovieData.getInvalidID()).get();
    }

}