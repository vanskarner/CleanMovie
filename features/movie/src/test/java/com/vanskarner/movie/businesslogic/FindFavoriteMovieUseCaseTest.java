package com.vanskarner.movie.businesslogic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class FindFavoriteMovieUseCaseTest {
    FindFavoriteMovieUseCase useCase;
    MovieLocalRepository fakeLocalRepository;

    @Before
    public void setUp() {
        fakeLocalRepository = FakeRepositoryFactory.createLocalRepository();

        useCase = new FindFavoriteMovieUseCase(fakeLocalRepository);
    }

    @Test
    public void execute_withValidID_returnItem() throws Exception {
        MovieDetailDS expected = MovieDetailDS.empty();
        expected.basicInfo.id = 1;
        fakeLocalRepository.saveMovie(expected).await();
        MovieDetailDS actual = useCase.execute(expected.basicInfo.id).get();

        assertEquals(expected.basicInfo.id, actual.basicInfo.id);
        assertEquals(expected.basicInfo.title, actual.basicInfo.title);
        assertEquals(expected.basicInfo.image, actual.basicInfo.image);
        assertEquals(expected.backgroundImage, actual.backgroundImage);
        assertEquals(expected.voteCount, actual.voteCount);
        assertEquals(expected.voteAverage, actual.voteAverage, 0.01);
        assertEquals(expected.releaseDate, actual.releaseDate);
        assertEquals(expected.overview, actual.overview);
    }

    @Test(expected = NoSuchElementException.class)
    public void execute_withInvalidID_throwNoSuchElementException() throws Exception {
        useCase.execute(0).get();
    }

}