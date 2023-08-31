package com.vanskarner.usecases.movie;

import static org.junit.Assert.assertEquals;

import com.vanskarner.usecases.movie.ds.MoviesDS;
import com.vanskarner.usecases.movie.repository.FakeRepositoryFactory;
import com.vanskarner.usecases.movie.repository.MovieLocalRepository;

import org.junit.Before;
import org.junit.Test;

public class ShowFavoriteMoviesUseCaseTest {
    ShowFavoriteMoviesUseCase useCase;
    MovieLocalRepository fakeLocalRepository;

    @Before
    public void setUp() {
        fakeLocalRepository = FakeRepositoryFactory.createLocalRepository();
        fakeLocalRepository.saveMovie(new MovieBOBuilder()
                .withId(1)
                .build());
        fakeLocalRepository.saveMovie(new MovieBOBuilder()
                .withId(2)
                .build());

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