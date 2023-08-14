package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.repository.FakeMovieRemoteRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowUpcomingMoviesUseCaseTest {
    ShowUpcomingMoviesUseCase useCase;
    FakeMovieRemoteRepository fakeMovieRemoteRepository;

    @Before
    public void setUp() {
        fakeMovieRemoteRepository = FakeRepositoryFactory.createMovieRemoteRepository();
        List<MovieBO> list = new ArrayList<>();
        list.add(MovieBOBuilder.getInstance()
                .withId(1)
                .build());
        list.add(MovieBOBuilder.getInstance()
                .withId(2)
                .build());
        fakeMovieRemoteRepository.setList(list);

        useCase = new ShowUpcomingMoviesUseCase(fakeMovieRemoteRepository);
    }

    @After
    public void tearDown() {
        fakeMovieRemoteRepository.clear();
    }

    @Test
    public void execute_returnList() {
        MoviesDS moviesDS = useCase.execute(1).get();
        int actualQuantity = moviesDS.list.size();
        int expectedQuantity = fakeMovieRemoteRepository.getList().size();

        assertEquals(expectedQuantity, actualQuantity);
    }

}