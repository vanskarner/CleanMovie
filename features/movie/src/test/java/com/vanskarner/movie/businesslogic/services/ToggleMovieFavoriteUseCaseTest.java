package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.error.MovieError;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import org.junit.Before;
import org.junit.Test;

public class ToggleMovieFavoriteUseCaseTest {

    MovieLocalRepository fakeLocalRepository;
    ToggleMovieFavoriteUseCase useCase;

    @Before
    public void setUp() {
        fakeLocalRepository = FakeRepositoryFactory.createLocalRepository();
        MovieErrorFilter domainErrorFilter = new MockMovieErrorFilter();

        useCase = new ToggleMovieFavoriteUseCase(fakeLocalRepository, domainErrorFilter);
    }

    @Test
    public void execute_withUnregisteredItem_savedItem() throws Exception {
        MovieDetailDS unregisteredItem = MovieDetailDS.empty();
        unregisteredItem.id = 1;
        boolean favorite = useCase.execute(unregisteredItem).get();
        int actualNumberItems = fakeLocalRepository.getNumberMovies().get();
        int expectedNumberItems = 1;

        assertTrue(favorite);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void execute_withRegisteredItem_deletedItem() throws Exception {
        MovieDetailDS registeredItem = MovieDetailDS.empty();
        registeredItem.id = 1;
        fakeLocalRepository.saveMovie(registeredItem).await();
        boolean favorite = useCase.execute(registeredItem).get();
        int actualNumberItems = fakeLocalRepository.getNumberMovies().get();
        int expectedNumberItems = 0;

        assertFalse(favorite);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test(expected = MovieError.FavoriteLimit.class)
    public void execute_withUnregisteredItemAndExceededCapacity_exception() throws Exception {
        MovieDetailDS item1 = MovieDetailDS.empty();
        MovieDetailDS item2 = MovieDetailDS.empty();
        item1.id = 1;
        item2.id = 2;
        fakeLocalRepository.saveMovie(item1).await();
        fakeLocalRepository.saveMovie(item2).await();
        MovieDetailDS unregisteredItem = MovieDetailDS.empty();
        unregisteredItem.id = 3;
        useCase.execute(unregisteredItem).get();
    }

}