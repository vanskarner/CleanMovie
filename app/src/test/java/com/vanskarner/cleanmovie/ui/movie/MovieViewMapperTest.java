package com.vanskarner.cleanmovie.ui.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesFilterDS;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieViewMapperTest {

    @Test
    public void convert_fromMovieDetailDS_toMovieDetailModel() {
        MovieDetailDS expectedItem = createMovieDetailDS();
        MovieDetailModel actualItem = MovieViewMapper.convert(expectedItem);

        assertEquals(expectedItem.id, actualItem.id);
        assertEquals(expectedItem.title, actualItem.title);
        assertEquals(expectedItem.image, actualItem.image);
        assertEquals(expectedItem.backgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
        assertEquals(expectedItem.recommended, actualItem.recommended);
    }

    @Test
    public void convert_fromMovieDetailModel_toMovieDetailDS() {
        MovieDetailModel expectedItem = createMovieDetailModel();
        MovieDetailDS actualItem = MovieViewMapper.convert(expectedItem);

        assertEquals(expectedItem.id, actualItem.id);
        assertEquals(expectedItem.title, actualItem.title);
        assertEquals(expectedItem.image, actualItem.image);
        assertEquals(expectedItem.backgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
        assertEquals(expectedItem.recommended, actualItem.recommended);
    }

    @Test
    public void convert_fromListMovieDS_toListMovieModel() {
        List<MovieDS> expectedList = new ArrayList<>(Collections.singletonList(createMovieDS()));
        List<MovieModel> actualList = MovieViewMapper.convert(expectedList);

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).id, actualList.get(0).id);
        assertEquals(expectedList.get(0).title, actualList.get(0).title);
        assertEquals(expectedList.get(0).image, actualList.get(0).image);
    }

    @Test
    public void convert_fromListMovieModel_toMoviesFilterDS() {
        List<MovieModel> expectedList = new ArrayList<>(Collections
                .singletonList(createMovieModel()));
        String expectedQuery = "My Search";
        MoviesFilterDS moviesFilterDS = MovieViewMapper.convert(expectedList, expectedQuery);
        List<MovieDS> actualList = moviesFilterDS.fullList;
        String actualQuery = moviesFilterDS.query.toString();

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).id, actualList.get(0).id);
        assertEquals(expectedList.get(0).title, actualList.get(0).title);
        assertEquals(expectedList.get(0).image, actualList.get(0).image);
        assertEquals(expectedQuery, actualQuery);
        assertTrue(moviesFilterDS.filterList.isEmpty());
    }

    private MovieDetailDS createMovieDetailDS() {
        return new MovieDetailDS(
                1,
                "Any title",
                "Any image",
                "Any background image",
                75,
                7.4f,
                "2023-01-12",
                "Any overview",
                true);
    }

    private MovieDetailModel createMovieDetailModel() {
        return new MovieDetailModel(
                1,
                "Any title",
                "Any image",
                "Any background image",
                75,
                7.4f,
                "2023-01-12",
                "Any overview",
                true);
    }

    private MovieDS createMovieDS() {
        return new MovieDS(1, "Any title", "Any Image");
    }

    private MovieModel createMovieModel() {
        return new MovieModel(1, "Any title", "Any Image");
    }

}