package com.vanskarner.usecases.movie;

import static org.junit.Assert.assertEquals;

import com.vanskarner.entities.MovieBO;
import com.vanskarner.usecases.movie.ds.MoviesDS;
import com.vanskarner.usecases.movie.repository.FakeRepositoryFactory;
import com.vanskarner.usecases.movie.repository.MovieRemoteRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowUpcomingMoviesUseCaseTest {
    ShowUpcomingMoviesUseCase useCase;
    MovieRemoteRepository fakeRemoteRepository;

    @Before
    public void setUp() {
        List<MovieBO> data = new ArrayList<>();
        data.add(new MovieBOBuilder()
                .withId(1)
                .build());
        data.add(new MovieBOBuilder()
                .withId(2)
                .build());
        fakeRemoteRepository = FakeRepositoryFactory.createRemoteRepository(data);

        useCase = new ShowUpcomingMoviesUseCase(fakeRemoteRepository);
    }

    @Test
    public void execute_returnList() throws Exception {
        MoviesDS moviesDS = useCase.execute(1).get();
        int actualQuantity = moviesDS.list.size();
        int expectedQuantity = fakeRemoteRepository.getMovies(1).get().size();

        assertEquals(expectedQuantity, actualQuantity);
    }

}