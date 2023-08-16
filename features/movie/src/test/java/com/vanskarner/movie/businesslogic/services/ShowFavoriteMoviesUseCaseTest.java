package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.repository.MovieLocalRepository;

import org.junit.Before;
import org.junit.Test;

public class ShowFavoriteMoviesUseCaseTest {
    ShowFavoriteMoviesUseCase useCase;
    MovieLocalRepository fakeRepository;

    @Before
    public void setUp() {
        fakeRepository = FakeRepositoryFactory.createMovieLocalRepository();
        fakeRepository.saveMovie(MovieBOBuilder.getInstance()
                .withId(1)
                .build());
        fakeRepository.saveMovie(MovieBOBuilder.getInstance()
                .withId(2)
                .build());

        useCase = new ShowFavoriteMoviesUseCase(fakeRepository);
    }

    @Test
    public void execute_returnList() throws Exception {
        MoviesDS moviesDS = useCase.execute().get();
        int actualQuantity = moviesDS.list.size();
        int expectedQuantity = fakeRepository.getNumberMovies().get();

        assertEquals(expectedQuantity, actualQuantity);
    }

}