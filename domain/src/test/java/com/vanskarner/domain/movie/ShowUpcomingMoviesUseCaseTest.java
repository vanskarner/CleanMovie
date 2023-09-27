package com.vanskarner.domain.movie;

import static org.junit.Assert.assertEquals;

import com.vanskarner.domain.movie.service.MoviesDS;

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
        int expectedQuantity = fakeRemoteRepository.getMovies(1).get().size();
        MoviesDS moviesDS = useCase.execute(1).get();
        int actualQuantity = moviesDS.list.size();

        assertEquals(expectedQuantity, actualQuantity);
    }

}