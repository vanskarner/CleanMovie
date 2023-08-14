package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.repository.FakeMovieLocalRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DeleteAllFavoriteMoviesUseCaseTest {
    FakeMovieLocalRepository fakeRepository;
    DeleteAllFavoriteMoviesUseCase useCase;

    @Before
    public void setUp() {
        fakeRepository = FakeRepositoryFactory.createMovieLocalRepository();
        List<MovieBO> list = new ArrayList<>();
        list.add(MovieBOBuilder.getInstance()
                .withId(1)
                .build());
        list.add(MovieBOBuilder.getInstance()
                .withId(2)
                .build());
        fakeRepository.setList(list);

        useCase = new DeleteAllFavoriteMoviesUseCase(fakeRepository);
    }

    @After
    public void tearDown() {
        fakeRepository.clear();
    }

    @Test
    public void execute_numberItemsDeleted() {
        int expectedNumberItems = fakeRepository.getList().size();
        int actualNumberItems = useCase.execute().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

}