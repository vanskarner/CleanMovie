package com.vanskarner.cleanmovie.ui.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.MovieBasicDS;
import com.vanskarner.movie.businesslogic.MovieDetailDS;
import com.vanskarner.movie.businesslogic.MoviesFilterDS;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieModelMapperTest {
    static MovieDetailDS movieDetailDS;
    static MovieDetailModel movieDetailModel;
    static MovieBasicDS movieBasicDS;
    static MovieBasicModel movieBasicModel;

    @BeforeClass
    public static void setupClass() {
        movieBasicDS = new MovieBasicDS(1,
                "Clean Architecture",
                "https://blog.cleancoder.com/anyImage.jpg");
        movieBasicModel = new MovieBasicModel(1,
                "Clean Architecture",
                "https://blog.cleancoder.com/anyImage.jpg");
        movieDetailDS = new MovieDetailDS(
                movieBasicDS,
                "Any image in base64 or http",
                100,
                9.5f,
                "2023-08-15",
                "Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time.",
                true);
        movieDetailModel = new MovieDetailModel(
                movieBasicModel,
                "Any image in base64 or http",
                100,
                9.5f,
                "2023-08-15",
                "Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time.",
                true);
    }

    @Test
    public void convert_fromMovieDetailDS_toMovieDetailModel() {
        MovieDetailDS expectedItem = movieDetailDS;
        MovieDetailModel actualItem = MovieModelMapper.convert(expectedItem);

        assertEquals(expectedItem.basicInfo.id, actualItem.basicModel.id);
        assertEquals(expectedItem.basicInfo.title, actualItem.basicModel.title);
        assertEquals(expectedItem.basicInfo.image, actualItem.basicModel.image);
        assertEquals(expectedItem.backgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
        assertEquals(expectedItem.recommended, actualItem.recommended);
    }

    @Test
    public void convert_fromMovieDetailModel_toMovieDetailDS() {
        MovieDetailModel expectedItem = movieDetailModel;
        MovieDetailDS actualItem = MovieModelMapper.convert(expectedItem);

        assertEquals(expectedItem.basicModel.id, actualItem.basicInfo.id);
        assertEquals(expectedItem.basicModel.title, actualItem.basicInfo.title);
        assertEquals(expectedItem.basicModel.image, actualItem.basicInfo.image);
        assertEquals(expectedItem.backgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
        assertEquals(expectedItem.recommended, actualItem.recommended);
    }

    @Test
    public void convert_fromMovieDSList_toMovieModelList() {
        List<MovieBasicDS> expectedList = new ArrayList<>(Collections.singletonList(movieBasicDS));
        List<MovieBasicModel> actualList = MovieModelMapper.convert(expectedList);

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).id, actualList.get(0).id);
        assertEquals(expectedList.get(0).title, actualList.get(0).title);
        assertEquals(expectedList.get(0).image, actualList.get(0).image);
    }

    @Test
    public void convert_fromMovieModelListAndQuery_toMoviesFilterDS() {
        List<MovieBasicModel> expectedList = new ArrayList<>(Collections.singletonList(movieBasicModel));
        String expectedQuery = "My Search";
        MoviesFilterDS moviesFilterDS = MovieModelMapper.convert(expectedList, expectedQuery);
        List<MovieBasicDS> actualList = moviesFilterDS.fullList;
        String actualQuery = moviesFilterDS.query.toString();

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).id, actualList.get(0).id);
        assertEquals(expectedList.get(0).title, actualList.get(0).title);
        assertEquals(expectedList.get(0).image, actualList.get(0).image);
        assertEquals(expectedQuery, actualQuery);
        assertTrue(moviesFilterDS.filterList.isEmpty());
    }

}