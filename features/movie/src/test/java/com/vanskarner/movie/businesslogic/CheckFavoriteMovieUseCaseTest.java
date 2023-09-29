package com.vanskarner.movie.businesslogic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CheckFavoriteMovieUseCaseTest {
    MovieLocalRepository fakeLocalRepository;
    CheckFavoriteMovieUseCase useCase;

    @Before
    public void setUp() {
        fakeLocalRepository = FakeRepositoryFactory.createLocalRepository();

        useCase = new CheckFavoriteMovieUseCase(fakeLocalRepository);
    }

    @Test
    public void execute_withValidID_itemExists() throws Exception {
        MovieBO item = new MovieBOBuilder()
                .withId(1)
                .build();
        fakeLocalRepository.saveMovie(item).await();
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