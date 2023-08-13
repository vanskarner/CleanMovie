package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.RepositoryFactory;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.error.MovieError;
import com.vanskarner.movie.businesslogic.repository.FakeRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActionFavoriteMovieUseCaseTest {

    FakeRepository fakeRepository;
    ActionFavoriteMovieUseCase useCase;
    MovieDetailDS registeredItem = new MovieDetailDS(1, "", "", "", 0, 0, "", "");
    MovieDetailDS unregisteredItem = new MovieDetailDS(2, "", "", "", 0, 0, "", "");

    @Before
    public void setUp() {
        fakeRepository = RepositoryFactory.createRepository();
        fakeRepository.saveMovie(MovieMapper.convert(registeredItem)).await();
        MovieErrorFilter domainErrorFilter = new MockMovieErrorFilter();

        useCase = new ActionFavoriteMovieUseCase(fakeRepository, domainErrorFilter);
    }

    @After
    public void tearDown() {
        fakeRepository.clear();
    }

    @Test
    public void executeWithLocalCapacityExceeded_registeredItem_returnFalse() {
        MovieBO otherItem = MovieBOBuilder.getInstance()
                .withId(3)
                .build();
        fakeRepository.saveMovie(otherItem).await();
        boolean isSaved = useCase.execute(registeredItem).get();

        assertFalse(isSaved);
    }

    @Test(expected = MovieError.FavoriteLimit.class)
    public void executeWithLocalCapacityExceeded_itemNotRegistered_FavoriteMovieLimit() {
        MovieBO otherItem = MovieBOBuilder.getInstance()
                .withId(3)
                .build();
        fakeRepository.saveMovie(otherItem).await();
        useCase.execute(unregisteredItem).get();
    }

    @Test
    public void executeWithLocalCapacityNotExceeded_registeredItem_returnFalse() {
        boolean actualValue = useCase
                .execute(registeredItem)
                .get();

        assertFalse(actualValue);
    }

    @Test
    public void executeWithLocalCapacityNotExceeded_itemNotRegistered_returnTrue() {
        boolean actualValue = useCase
                .execute(unregisteredItem)
                .get();

        assertTrue(actualValue);
    }

}