package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.error.MovieError;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import org.junit.Before;
import org.junit.Test;

public class ToggleMovieFavoriteUseCaseTest {

    MovieLocalRepository fakeLocalRepository;
    ToggleMovieFavoriteUseCase useCase;

    @Before
    public void setUp() {
        fakeLocalRepository = FakeRepositoryFactory.createFakeLocalRepository();
        MovieErrorFilter domainErrorFilter = new MockMovieErrorFilter();

        useCase = new ToggleMovieFavoriteUseCase(fakeLocalRepository, domainErrorFilter);
    }

    @Test
    public void execute_withUnregisteredItem_savedItem() throws Exception {
        MovieBO unregisteredItem = new MovieBOBuilder()
                .withId(1)
                .build();
        boolean favorite = useCase.execute(MovieMapper.convert(unregisteredItem)).get();
        int actualNumberItems = fakeLocalRepository.getNumberMovies().get();
        int expectedNumberItems = 1;

        assertTrue(favorite);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void execute_withRegisteredItem_deletedItem() throws Exception {
        MovieBO registeredItem = new MovieBOBuilder()
                .withId(1)
                .build();
        fakeLocalRepository.saveMovie(registeredItem).await();
        boolean favorite = useCase.execute(MovieMapper.convert(registeredItem)).get();
        int actualNumberItems = fakeLocalRepository.getNumberMovies().get();
        int expectedNumberItems = 0;

        assertFalse(favorite);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test(expected = MovieError.FavoriteLimit.class)
    public void execute_withUnregisteredItemAndExceededCapacity_exception() throws Exception {
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