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
        MovieDetailDS expectedDS = new MovieDetailDS(1, "Clean movie DS", "any image",
                "any background image", 75, 7.4f,
                "2023-01-12", "My overview", true);
        MovieDetailModel actualModel = MovieViewMapper.convert(expectedDS);

        assertEquals(expectedDS.id, actualModel.id);
        assertEquals(expectedDS.title, actualModel.title);
        assertEquals(expectedDS.image, actualModel.image);
        assertEquals(expectedDS.backgroundImage, actualModel.backgroundImage);
        assertEquals(expectedDS.voteCount, actualModel.voteCount);
        assertEquals(expectedDS.voteAverage, actualModel.voteAverage, 0.01);
        assertEquals(expectedDS.releaseDate, actualModel.releaseDate);
        assertEquals(expectedDS.overview, actualModel.overview);
        assertEquals(expectedDS.recommended, actualModel.recommended);
    }

    @Test
    public void convert_fromMovieDetailModel_toMovieDetailDS() {
        MovieDetailModel expectedModel = new MovieDetailModel(2, "Clean movie Model",
                "any image", "any background image", 75,
                8.4f, "2023-01-15", "My overview", true);
        MovieDetailDS actualDS = MovieViewMapper.convert(expectedModel);

        assertEquals(expectedModel.id, actualDS.id);
        assertEquals(expectedModel.title, actualDS.title);
        assertEquals(expectedModel.image, actualDS.image);
        assertEquals(expectedModel.backgroundImage, actualDS.backgroundImage);
        assertEquals(expectedModel.voteCount, actualDS.voteCount);
        assertEquals(expectedModel.voteAverage, actualDS.voteAverage, 0.01);
        assertEquals(expectedModel.releaseDate, actualDS.releaseDate);
        assertEquals(expectedModel.overview, actualDS.overview);
        assertEquals(expectedModel.recommended, actualDS.recommended);
    }

    @Test
    public void convert_fromListMovieDS_toListMovieModel() {
        MovieDS movieDS = new MovieDS(3, "Clean Movie", "Any Image");
        List<MovieDS> expectedListDS = new ArrayList<>(Collections.singletonList(movieDS));
        List<MovieModel> actualListModel = MovieViewMapper.convert(expectedListDS);

        assertEquals(expectedListDS.size(), actualListModel.size());
        assertEquals(expectedListDS.get(0).id, actualListModel.get(0).id);
        assertEquals(expectedListDS.get(0).title, actualListModel.get(0).title);
        assertEquals(expectedListDS.get(0).image, actualListModel.get(0).image);
    }

    @Test
    public void convert_fromListMovieModel_toMoviesFilterDS() {
        String expectedQuery = "My Search";
        MovieModel movieModel = new MovieModel(4, "Clean Movie", "Any Image");
        List<MovieModel> expectedListModel = new ArrayList<>(Collections.singletonList(movieModel));
        MoviesFilterDS actualModelDS = MovieViewMapper.convert(expectedListModel, expectedQuery);

        assertEquals(expectedListModel.size(), actualModelDS.fullList.size());
        assertEquals(expectedListModel.get(0).id, actualModelDS.fullList.get(0).id);
        assertEquals(expectedListModel.get(0).title, actualModelDS.fullList.get(0).title);
        assertEquals(expectedListModel.get(0).image, actualModelDS.fullList.get(0).image);
        assertEquals(expectedQuery, actualModelDS.query);
        assertTrue(actualModelDS.filterList.isEmpty());
    }

}