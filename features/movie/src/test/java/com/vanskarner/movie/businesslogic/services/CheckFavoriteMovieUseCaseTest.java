package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.repository.FakeMovieLocalRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckFavoriteMovieUseCaseTest {
    FakeMovieLocalRepository fakeRepository;
    CheckFavoriteMovieUseCase useCase;

    @Before
    public void setUp() {
        fakeRepository = FakeRepositoryFactory.createMovieLocalRepository();

        useCase = new CheckFavoriteMovieUseCase(fakeRepository);
    }

    @After
    public void tearDown() {
        fakeRepository.clear();
    }

    @Test
    public void execute_withValidID_itemExists() {
        MovieBO item = MovieBOBuilder.getInstance()
                .withId(1)
                .build();
        fakeRepository.addItem(item);
        boolean exists = useCase
                .execute(item.getId())
                .get();

        assertTrue(exists);
    }

    @Test
    public void execute_withInvalidID_itemNotExists() {
        boolean exists = useCase
                .execute(1)
                .get();

        assertFalse(exists);
    }

}