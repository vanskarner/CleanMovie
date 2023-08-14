package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.repository.FakeMovieRemoteRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class FindUpcomingMovieUseCaseTest {
    FindUpcomingMovieUseCase useCase;
    FakeMovieRemoteRepository fakeRepository;

    @Before
    public void setUp() {
        fakeRepository = FakeRepositoryFactory.createMovieRemoteRepository();

        useCase = new FindUpcomingMovieUseCase(fakeRepository);
    }

    @After
    public void tearDown() {
        fakeRepository.clear();
    }

    @Test
    public void execute_withValidID_returnItem() {
        MovieBO expected = MovieBOBuilder.getInstance()
                .withId(1)
                .build();
        fakeRepository.addItem(expected);
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
    public void execute_withInvalidID_throwException() {
        useCase.execute(0).get();
    }

}