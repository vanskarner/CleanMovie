package com.vanskarner.movie.businesslogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ToggleMovieFavoriteUseCaseTest {

    MovieLocalRepository fakeLocalRepository;
    ToggleMovieFavoriteUseCase useCase;

    @Before
    public void setUp() {
        fakeLocalRepository = FakeRepositoryFactory.createLocalRepository();
        MovieErrorFilter domainErrorFilter = new MockMovieErrorFilter();
        CheckFavoriteMovieUseCase checkFavoriteMovieUseCase =
                new CheckFavoriteMovieUseCase(fakeLocalRepository);

        useCase = new ToggleMovieFavoriteUseCase(
                checkFavoriteMovieUseCase,
                fakeLocalRepository,
                domainErrorFilter);
    }

    @Test
    public void execute_withUnregisteredItem_savedItem() throws Exception {
        int expectedNumberItems = 1;
        MovieBO unregisteredItem = new MovieBOBuilder()
                .withId(1)
                .build();
        boolean favorite = useCase.execute(MovieMapper.convert(unregisteredItem)).get();
        int actualNumberItems = fakeLocalRepository.getNumberMovies().get();

        assertTrue(favorite);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void execute_withRegisteredItem_deletedItem() throws Exception {
        int expectedNumberItems = 0;
        MovieBO registeredItem = new MovieBOBuilder()
                .withId(1)
                .build();
        fakeLocalRepository.saveMovie(registeredItem).await();
        boolean favorite = useCase.execute(MovieMapper.convert(registeredItem)).get();
        int actualNumberItems = fakeLocalRepository.getNumberMovies().get();

        assertFalse(favorite);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test(expected = MovieError.FavoriteLimit.class)
    public void execute_withUnregisteredItemAndExceededCapacity_throwException() throws Exception {
        fakeLocalRepository.saveMovie(new MovieBOBuilder()
                .withId(1)
                .build()).await();
        fakeLocalRepository.saveMovie(new MovieBOBuilder()
                .withId(2)
                .build()).await();
        MovieBO unregisteredItem = new MovieBOBuilder()
                .withId(3)
                .build();
        useCase.execute(MovieMapper.convert(unregisteredItem)).get();
    }

}