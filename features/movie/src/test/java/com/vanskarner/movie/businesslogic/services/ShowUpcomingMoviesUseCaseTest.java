package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.repository.MovieRemoteRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowUpcomingMoviesUseCaseTest {
    ShowUpcomingMoviesUseCase useCase;
    MovieRemoteRepository fakeMovieRemoteRepository;

    @Before
    public void setUp() {
        List<MovieBO> data = new ArrayList<>();
        data.add(MovieBOBuilder.getInstance()
                .withId(1)
                .build());
        data.add(MovieBOBuilder.getInstance()
                .withId(2)
                .build());
        fakeMovieRemoteRepository = FakeRepositoryFactory.createMovieRemoteRepository(data);

        useCase = new ShowUpcomingMoviesUseCase(fakeMovieRemoteRepository);
    }

    @Test
    public void execute_returnList() throws Exception {
        MoviesDS moviesDS = useCase.execute(1).get();
        int actualQuantity = moviesDS.list.size();
        int expectedQuantity = fakeMovieRemoteRepository.getMovies(1).get().size();

        assertEquals(expectedQuantity, actualQuantity);
    }

}