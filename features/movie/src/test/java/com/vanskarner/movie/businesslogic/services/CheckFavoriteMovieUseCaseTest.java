package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

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
        MovieDetailDS item = MovieDetailDS.empty();
        item.basicInfo.id = 1;
        fakeLocalRepository.saveMovie(item).await();
        boolean exists = useCase
                .execute(item.basicInfo.id)
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