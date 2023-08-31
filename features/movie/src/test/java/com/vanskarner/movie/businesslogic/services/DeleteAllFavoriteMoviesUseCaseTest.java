package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import org.junit.Before;
import org.junit.Test;

public class DeleteAllFavoriteMoviesUseCaseTest {
    MovieLocalRepository fakeLocalRepository;
    DeleteAllFavoriteMoviesUseCase useCase;

    @Before
    public void setUp() {
        fakeLocalRepository = FakeRepositoryFactory.createLocalRepository();
        MovieDetailDS item1 = MovieDetailDS.empty();
        MovieDetailDS item2 = MovieDetailDS.empty();
        item1.id = 1;
        item2.id = 2;
        fakeLocalRepository.saveMovie(item1);
        fakeLocalRepository.saveMovie(item2);

        useCase = new DeleteAllFavoriteMoviesUseCase(fakeLocalRepository);
    }

    @Test
    public void execute_numberItemsDeleted() throws Exception {
        int expectedNumberItems = fakeLocalRepository.getNumberMovies().get();
        int actualNumberItems = useCase.execute().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

}