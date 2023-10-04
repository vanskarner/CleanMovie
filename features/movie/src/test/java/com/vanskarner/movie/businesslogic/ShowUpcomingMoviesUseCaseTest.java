package com.vanskarner.movie.businesslogic;

import static org.junit.Assert.assertEquals;

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
        item1.basicInfo.id = 1;
        item2.basicInfo.id = 2;
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