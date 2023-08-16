package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import org.junit.Before;
import org.junit.Test;

public class DeleteAllFavoriteMoviesUseCaseTest {
    MovieLocalRepository fakeRepository;
    DeleteAllFavoriteMoviesUseCase useCase;

    @Before
    public void setUp() {
        fakeRepository = FakeRepositoryFactory.createMovieLocalRepository();
        fakeRepository.saveMovie(MovieBOBuilder.getInstance()
                .withId(1)
                .build());
        fakeRepository.saveMovie(MovieBOBuilder.getInstance()
                .withId(2)
                .build());

        useCase = new DeleteAllFavoriteMoviesUseCase(fakeRepository);
    }

    @Test
    public void execute_numberItemsDeleted() throws Exception {
        int expectedNumberItems = fakeRepository.getNumberMovies().get();
        int actualNumberItems = useCase.execute().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

}