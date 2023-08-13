package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.RepositoryFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.repository.FakeRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeleteAllFavoriteMoviesUseCaseTest {
    FakeRepository fakeRepository;
    DeleteAllFavoriteMoviesUseCase useCase;

    @Before
    public void setUp() {
        fakeRepository = RepositoryFactory.createRepository();
        MovieBO firstItem = MovieBOBuilder.getInstance()
                .withId(1)
                .build();
        MovieBO secondItem = MovieBOBuilder.getInstance()
                .withId(2)
                .build();
        fakeRepository.saveMovie(firstItem).await();
        fakeRepository.saveMovie(secondItem).await();

        useCase = new DeleteAllFavoriteMoviesUseCase(fakeRepository);
    }

    @After
    public void tearDown() {
        fakeRepository.clear();
    }

    @Test
    public void execute_totalItemsDeleted() {
        int expectedNumberItems = fakeRepository.getNumberMovies().get();
        int actualNumberItems = useCase.execute().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

}