package com.vanskarner.domain.movie;

import static org.junit.Assert.assertEquals;

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
        int expectedQuantity = fakeLocalRepository.getNumberMovies().get();
        MoviesDS moviesDS = useCase.execute().get();
        int actualQuantity = moviesDS.list.size();

        assertEquals(expectedQuantity, actualQuantity);
    }

}