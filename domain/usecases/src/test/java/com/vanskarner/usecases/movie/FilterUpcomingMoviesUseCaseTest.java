package com.vanskarner.usecases.movie;

import static org.junit.Assert.assertEquals;

import com.vanskarner.core.sync.Result;
import com.vanskarner.usecases.movie.ds.MovieDS;
import com.vanskarner.usecases.movie.ds.MoviesFilterDS;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterUpcomingMoviesUseCaseTest {
    static List<MovieDS> unmodifiableList;
    static FilterUpcomingMoviesUseCase useCase;

    @BeforeClass
    public static void setUp() {
        List<MovieDS> mutableList = new ArrayList<>();
        mutableList.add(new MovieDS(1, "Movie One", "any"));
        mutableList.add(new MovieDS(2, "Movie Two", "any"));
        mutableList.add(new MovieDS(3, "Movie Three", "any"));
        unmodifiableList = Collections.unmodifiableList(new ArrayList<>(mutableList));

        useCase = new FilterUpcomingMoviesUseCase();
    }

    @Test
    public void execute_exactSearch_oneMatch() throws Exception {
        MoviesFilterDS filterDS = new MoviesFilterDS(unmodifiableList, "Movie One");
        Result<MoviesFilterDS> result = useCase.execute(filterDS);
        int actualValue = result.get().filterList.size();
        int expectedMatches = 1;

        assertEquals(expectedMatches, actualValue);
    }

    @Test
    public void execute_impreciseSearch_multipleMatches() throws Exception {
        MoviesFilterDS filterDS = new MoviesFilterDS(unmodifiableList, "Movie");
        Result<MoviesFilterDS> result = useCase.execute(filterDS);
        int actualMatches = result.get().filterList.size();
        int expectedMatches = unmodifiableList.size();

        assertEquals(expectedMatches, actualMatches);
    }

    @Test
    public void execute_wrongSearch_noMatch() throws Exception {
        MoviesFilterDS filterDS = new MoviesFilterDS(unmodifiableList, "Nothing");
        Result<MoviesFilterDS> result = useCase.execute(filterDS);
        int actualMatches = result.get().filterList.size();
        int expectedMatches = 0;

        assertEquals(expectedMatches, actualMatches);
    }

}