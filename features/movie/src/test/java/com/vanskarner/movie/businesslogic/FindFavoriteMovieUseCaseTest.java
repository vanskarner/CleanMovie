package com.vanskarner.movie.businesslogic;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.ui.MovieDetailDS;

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
        MovieBO expected = new MovieBOBuilder()
                .withId(1)
                .build();
        fakeLocalRepository.saveMovie(expected).await();
        MovieDetailDS actual = useCase.execute(expected.getId()).get();

        assertEquals(expected.getId(), actual.movieBasicDS.id);
        assertEquals(expected.getTitle(), actual.movieBasicDS.title);
        assertEquals(expected.getImage(), actual.movieBasicDS.image);
        assertEquals(expected.getBackgroundImage(), actual.backgroundImage);
        assertEquals(expected.getVoteCount(), actual.voteCount);
        assertEquals(expected.getVoteAverage(), actual.voteAverage, 0.01);
        assertEquals(expected.getReleaseDate(), actual.releaseDate);
        assertEquals(expected.getOverview(), actual.overview);
    }

    @Test(expected = NoSuchElementException.class)
    public void execute_withInvalidID_throwNoSuchElementException() throws Exception {
        useCase.execute(0).get();
    }

}