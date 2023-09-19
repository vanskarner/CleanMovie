package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.core.sync.Result;
import com.vanskarner.movie.businesslogic.ds.MovieBasicDS;
import com.vanskarner.movie.businesslogic.ds.MoviesFilterDS;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterUpcomingMoviesUseCaseTest {
    static List<MovieBasicDS> unmodifiableList;
    static FilterUpcomingMoviesUseCase useCase;

    @BeforeClass
    public static void setUp() {
        List<MovieBasicDS> mutableList = new ArrayList<>();
        mutableList.add(new MovieBasicDS(1, "Movie One", "any"));
        mutableList.add(new MovieBasicDS(2, "Movie Two", "any"));
        mutableList.add(new MovieBasicDS(3, "Movie Three", "any"));
        unmodifiableList = Collections.unmodifiableList(new ArrayList<>(mutableList));

        useCase = new FilterUpcomingMoviesUseCase();
    }

    @Test
    public void execute_exactSearch_oneMatch() throws Exception {
        int expectedMatches = 1;
        MoviesFilterDS filterDS = new MoviesFilterDS(unmodifiableList, "Movie One");
        Result<MoviesFilterDS> result = useCase.execute(filterDS);
        int actualValue = result.get().filterList.size();

        assertEquals(expectedMatches, actualValue);
    }

    @Test
    public void execute_impreciseSearch_multipleMatches() throws Exception {
        int expectedMatches = unmodifiableList.size();
        MoviesFilterDS filterDS = new MoviesFilterDS(unmodifiableList, "Movie");
        Result<MoviesFilterDS> result = useCase.execute(filterDS);
        int actualMatches = result.get().filterList.size();

        assertEquals(expectedMatches, actualMatches);
    }

    @Test
    public void execute_wrongSearch_noMatch() throws Exception {
        int expectedMatches = 0;
        MoviesFilterDS filterDS = new MoviesFilterDS(unmodifiableList, "Nothing");
        Result<MoviesFilterDS> result = useCase.execute(filterDS);
        int actualMatches = result.get().filterList.size();

        assertEquals(expectedMatches, actualMatches);
    }

}