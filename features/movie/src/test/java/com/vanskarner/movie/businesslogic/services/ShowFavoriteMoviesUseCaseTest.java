package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import org.junit.Before;
import org.junit.Test;

public class ShowFavoriteMoviesUseCaseTest {
    ShowFavoriteMoviesUseCase useCase;
    MovieLocalRepository fakeLocalRepository;

    @Before
    public void setUp() {
        fakeLocalRepository = FakeRepositoryFactory.createLocalRepository();
        MovieDetailDS item1 = MovieDetailDS.empty();
        item1.id = 1;
        MovieDetailDS item2 = MovieDetailDS.empty();
        item2.id = 1;
        fakeLocalRepository.saveMovie(item1);
        fakeLocalRepository.saveMovie(item2);

        useCase = new ShowFavoriteMoviesUseCase(fakeLocalRepository);
    }

    @Test
    public void execute_returnList() throws Exception {
        MoviesDS moviesDS = useCase.execute().get();
        int actualQuantity = moviesDS.list.size();
        int expectedQuantity = fakeLocalRepository.getNumberMovies().get();

        assertEquals(expectedQuantity, actualQuantity);
    }

}