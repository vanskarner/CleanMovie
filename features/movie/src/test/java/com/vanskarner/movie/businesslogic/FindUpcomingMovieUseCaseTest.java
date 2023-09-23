package com.vanskarner.movie.businesslogic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Collections;
import java.util.NoSuchElementException;

public class FindUpcomingMovieUseCaseTest {

    @Test
    public void execute_withValidID_returnItem() throws Exception {
        MovieDetailDS expectedItem = MovieDetailDS.empty();
        expectedItem.basicInfo.id = 1;
        MovieRemoteRepository fakeRemoteRepository = FakeRepositoryFactory
                .createRemoteRepository(Collections.singletonList(expectedItem));
        FindUpcomingMovieUseCase useCase = new FindUpcomingMovieUseCase(fakeRemoteRepository);
        MovieDetailDS actualItem = useCase.execute(expectedItem.basicInfo.id).get();

        assertEquals(expectedItem.basicInfo.id, actualItem.basicInfo.id);
        assertEquals(expectedItem.basicInfo.title, actualItem.basicInfo.title);
        assertEquals(expectedItem.basicInfo.image, actualItem.basicInfo.image);
        assertEquals(expectedItem.backgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
        assertEquals(expectedItem.recommended, actualItem.recommended);
    }

    @Test(expected = NoSuchElementException.class)
    public void execute_withInvalidID_throwNoSuchElementException() throws Exception {
        MovieRemoteRepository fakeRemoteRepository = FakeRepositoryFactory
                .createRemoteRepository(Collections.emptyList());
        FindUpcomingMovieUseCase useCase = new FindUpcomingMovieUseCase(fakeRemoteRepository);

        useCase.execute(1).get();
    }

}