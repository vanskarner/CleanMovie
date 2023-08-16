package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import org.junit.Before;
import org.junit.Test;

public class CheckFavoriteMovieUseCaseTest {
    MovieLocalRepository fakeRepository;
    CheckFavoriteMovieUseCase useCase;

    @Before
    public void setUp() {
        fakeRepository = FakeRepositoryFactory.createMovieLocalRepository();

        useCase = new CheckFavoriteMovieUseCase(fakeRepository);
    }

    @Test
    public void execute_withValidID_itemExists() throws Exception {
        MovieBO item = MovieBOBuilder.getInstance()
                .withId(1)
                .build();
        fakeRepository.saveMovie(item).await();
        boolean exists = useCase
                .execute(item.getId())
                .get();

        assertTrue(exists);
    }

    @Test
    public void execute_withInvalidID_itemNotExists() throws Exception {
        boolean exists = useCase
                .execute(1)
                .get();

        assertFalse(exists);
    }

}