package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.RepositoryFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.repository.FakeRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckFavoriteMovieUseCaseTest {
    FakeRepository fakeRepository;
    CheckFavoriteMovieUseCase useCase;

    @Before
    public void setUp() {
        fakeRepository = RepositoryFactory.createRepository();

        useCase = new CheckFavoriteMovieUseCase(fakeRepository);
    }

    @After
    public void tearDown() {
        fakeRepository.clear();
    }

    @Test
    public void execute_withValidID_true() {
        MovieBO item = MovieBOBuilder.getInstance()
                .withId(1)
                .build();
        fakeRepository.saveMovie(item).await();
        boolean isItemPresent = useCase
                .execute(item.getId())
                .get();

        assertTrue(isItemPresent);
    }

    @Test
    public void execute_withInvalidID_false() {
        boolean isItemPresent = useCase
                .execute(0)
                .get();

        assertFalse(isItemPresent);
    }

}