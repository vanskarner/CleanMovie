package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MoviesFilterDS;
import com.vanskarner.movie.businesslogic.repository.MovieData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FilterUpcomingMoviesUseCaseTest {
    FilterUpcomingMoviesUseCase useCase;

    @Before
    public void setUp() {
        useCase = new FilterUpcomingMoviesUseCase();
    }

    @After
    public void tearDown() {
        useCase = null;
    }

    @Test
    public void execute_exactSearch_oneMatch() {
        List<MovieDS> fullList = MovieMapper.convert(MovieData.getData()).list;
        String query = "Puss in Boots";
        MoviesFilterDS filterDS = new MoviesFilterDS(fullList, query);
        useCase.execute(filterDS);
        int actualValue = filterDS.filterList.size();

        assertEquals(1, actualValue);
    }

    @Test
    public void execute_impreciseSearch_multipleMatches() {
        List<MovieDS> fullList = MovieMapper.convert(MovieData.getData()).list;
        String query = "the";
        MoviesFilterDS filterDS = new MoviesFilterDS(fullList, query);
        useCase.execute(filterDS);
        int actualValue = filterDS.filterList.size();

        assertTrue(actualValue > 1);
    }

    @Test
    public void execute_wrongSearch_noMatch() {
        List<MovieDS> fullList = MovieMapper.convert(MovieData.getData()).list;
        String query = "Nothing";
        MoviesFilterDS filterDS = new MoviesFilterDS(fullList, query);
        useCase.execute(filterDS);

        assertTrue(filterDS.filterList.isEmpty());
    }

}