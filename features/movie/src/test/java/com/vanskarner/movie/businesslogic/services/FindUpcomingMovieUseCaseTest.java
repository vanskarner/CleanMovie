package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.repository.MovieRemoteRepository;
import org.junit.Test;

import java.util.Collections;
import java.util.NoSuchElementException;

public class FindUpcomingMovieUseCaseTest {

    @Test
    public void execute_withValidID_returnItem() throws Exception {
        MovieBO expectedItem = MovieBOBuilder.getInstance()
                .withId(1)
                .build();
        MovieRemoteRepository fakeRemoteRepository = FakeRepositoryFactory
                .createMovieRemoteRepository(Collections.singletonList(expectedItem));
        FindUpcomingMovieUseCase useCase = new FindUpcomingMovieUseCase(fakeRemoteRepository);
        MovieDetailDS actualItem = useCase.execute(expectedItem.getId()).get();

        assertEquals(expectedItem.getId(), actualItem.id);
        assertEquals(expectedItem.getTitle(), actualItem.title);
        assertEquals(expectedItem.getImage(), actualItem.image);
        assertEquals(expectedItem.getBackgroundImage(), actualItem.backgroundImage);
        assertEquals(expectedItem.getVoteCount(), actualItem.voteCount);
        assertEquals(expectedItem.getVoteAverage(), actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.getReleaseDate(), actualItem.releaseDate);
        assertEquals(expectedItem.getOverview(), actualItem.overview);
        assertEquals(expectedItem.isRecommended(), actualItem.recommended);
    }

    @Test(expected = NoSuchElementException.class)
    public void execute_withInvalidID_throwException() throws Exception {
        MovieRemoteRepository fakeRemoteRepository = FakeRepositoryFactory
                .createMovieRemoteRepository(Collections.emptyList());
        FindUpcomingMovieUseCase useCase = new FindUpcomingMovieUseCase(fakeRemoteRepository);

        useCase.execute(1).get();
    }

}