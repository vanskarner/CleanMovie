package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.repository.MovieRemoteRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowUpcomingMoviesUseCaseTest {
    ShowUpcomingMoviesUseCase useCase;
    List<MovieDetailDS> data;

    @Before
    public void setUp() {
        data = new ArrayList<>();
        MovieDetailDS item1 = MovieDetailDS.empty();
        MovieDetailDS item2 = MovieDetailDS.empty();
        item1.id = 1;
        item2.id = 2;
        data.add(item1);
        data.add(item2);
        MovieRemoteRepository fakeRemoteRepository = FakeRepositoryFactory.createRemoteRepository(data);

        useCase = new ShowUpcomingMoviesUseCase(fakeRemoteRepository);
    }

    @Test
    public void execute_returnList() throws Exception {
        int expectedQuantity = data.size();
        MoviesDS moviesDS = useCase.execute(1).get();
        int actualQuantity = moviesDS.list.size();

        assertEquals(expectedQuantity, actualQuantity);
    }

}