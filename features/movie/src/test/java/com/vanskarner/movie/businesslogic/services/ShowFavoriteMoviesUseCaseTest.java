package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.repository.FakeMovieLocalRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowFavoriteMoviesUseCaseTest {
    ShowFavoriteMoviesUseCase useCase;
    FakeMovieLocalRepository fakeRepository;

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

        useCase = new ShowFavoriteMoviesUseCase(fakeRepository);
    }

    @After
    public void tearDown() {
        fakeRepository.clear();
    }

    @Test
    public void execute_returnList() {
        MoviesDS moviesDS = useCase.execute().get();
        int actualQuantity = moviesDS.list.size();
        int expectedQuantity = fakeRepository.getList().size();

        assertEquals(expectedQuantity, actualQuantity);
    }

}