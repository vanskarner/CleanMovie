package com.vanskarner.usecases.movie;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DeleteAllFavoriteMoviesUseCaseTest {
    MovieLocalRepository fakeLocalRepository;
    DeleteAllFavoriteMoviesUseCase useCase;

    @Before
    public void setUp() {
        fakeLocalRepository = FakeRepositoryFactory.createLocalRepository();
        fakeLocalRepository.saveMovie(new MovieBOBuilder()
                .withId(1)
                .build());
        fakeLocalRepository.saveMovie(new MovieBOBuilder()
                .withId(2)
                .build());

        useCase = new DeleteAllFavoriteMoviesUseCase(fakeLocalRepository);
    }

    @Test
    public void execute_numberItemsDeleted() throws Exception {
        int expectedNumberItems = fakeLocalRepository.getNumberMovies().get();
        int actualNumberItems = useCase.execute().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }


}