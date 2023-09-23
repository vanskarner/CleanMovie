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

        useCase = new ToggleMovieFavoriteUseCase(fakeLocalRepository, domainErrorFilter);
    }

    @Test
    public void execute_withUnregisteredItem_savedItem() throws Exception {
        int expectedNumberItems = 1;
        MovieDetailDS unregisteredItem = MovieDetailDS.empty();
        unregisteredItem.basicInfo.id = 1;
        boolean favorite = useCase.execute(unregisteredItem).get();
        int actualNumberItems = fakeLocalRepository.getNumberMovies().get();

        assertTrue(favorite);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void execute_withRegisteredItem_deletedItem() throws Exception {
        int expectedNumberItems = 0;
        MovieDetailDS registeredItem = MovieDetailDS.empty();
        registeredItem.basicInfo.id = 1;
        fakeLocalRepository.saveMovie(registeredItem).await();
        boolean favorite = useCase.execute(registeredItem).get();
        int actualNumberItems = fakeLocalRepository.getNumberMovies().get();

        assertFalse(favorite);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test(expected = MovieError.FavoriteLimit.class)
    public void execute_withUnregisteredItemAndExceededCapacity_throwException() throws Exception {
        MovieDetailDS item1 = MovieDetailDS.empty();
        MovieDetailDS item2 = MovieDetailDS.empty();
        item1.basicInfo.id = 1;
        item2.basicInfo.id = 2;
        fakeLocalRepository.saveMovie(item1).await();
        fakeLocalRepository.saveMovie(item2).await();
        MovieDetailDS unregisteredItem = MovieDetailDS.empty();
        unregisteredItem.basicInfo.id = 3;
        useCase.execute(unregisteredItem).get();
    }

}