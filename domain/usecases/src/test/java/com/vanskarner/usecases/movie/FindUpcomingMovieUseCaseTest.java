package com.vanskarner.usecases.movie;

import com.vanskarner.entities.MovieBO;
import com.vanskarner.usecases.movie.ds.MovieDetailDS;
import com.vanskarner.usecases.movie.repository.FakeRepositoryFactory;
import com.vanskarner.usecases.movie.repository.MovieRemoteRepository;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Collections;
import java.util.NoSuchElementException;

public class FindUpcomingMovieUseCaseTest {
    @Test
    public void execute_withValidID_returnItem() throws Exception {
        MovieBO expectedItem = new MovieBOBuilder()
                .withId(1)
                .build();
        MovieRemoteRepository fakeRemoteRepository = FakeRepositoryFactory
                .createRemoteRepository(Collections.singletonList(expectedItem));
        FindUpcomingMovieUseCase useCase = new FindUpcomingMovieUseCase(fakeRemoteRepository);
        MovieDetailDS actualItem = useCase.execute(expectedItem.getId()).get();

        assertEquals(expectedItem.getId(), actualItem.movieBasicDS.id);
        assertEquals(expectedItem.getTitle(), actualItem.movieBasicDS.title);
        assertEquals(expectedItem.getImage(), actualItem.movieBasicDS.image);
        assertEquals(expectedItem.getBackgroundImage(), actualItem.backgroundImage);
        assertEquals(expectedItem.getVoteCount(), actualItem.voteCount);
        assertEquals(expectedItem.getVoteAverage(), actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.getReleaseDate(), actualItem.releaseDate);
        assertEquals(expectedItem.getOverview(), actualItem.overview);
        assertEquals(expectedItem.isRecommended(), actualItem.recommended);
    }

    @Test(expected = NoSuchElementException.class)
    public void execute_withInvalidID_throwNoSuchElementException() throws Exception {
        MovieRemoteRepository fakeRemoteRepository = FakeRepositoryFactory
                .createRemoteRepository(Collections.emptyList());
        FindUpcomingMovieUseCase useCase = new FindUpcomingMovieUseCase(fakeRemoteRepository);
        useCase.execute(1).get();
    }

}