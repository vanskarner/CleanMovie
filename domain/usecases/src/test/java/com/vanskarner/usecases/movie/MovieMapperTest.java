package com.vanskarner.usecases.movie;

import com.vanskarner.entities.MovieBO;
import com.vanskarner.usecases.movie.ds.MovieBasicDS;
import com.vanskarner.usecases.movie.ds.MovieDetailDS;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieMapperTest {
    static MovieBO businessObject;
    static MovieDetailDS dataStructure;

    @BeforeClass
    public static void setup() {
        businessObject = new MovieBOBuilder()
                .withId(1)
                .withTitle("Clean Architecture")
                .withImage("https://blog.cleancoder.com/anyImage.jpg")
                .withBackgroundImage("https://blog.cleancoder.com/anyBackImage.jpg")
                .withVoteCount(100)
                .withVoteAverage(9.5f)
                .withReleaseDate("2023-08-15")
                .withOverview("Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time.")
                .build();
        dataStructure = new MovieDetailDS(
                new MovieBasicDS(1,
                        "Clean Architecture",
                        "https://blog.cleancoder.com/anyImage.jpg"),
                "https://blog.cleancoder.com/anyBackImage.jpg",
                100,
                9.5f,
                "2023-08-15",
                "Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time.");
    }

    @Test
    public void convert_fromMovieBO_toMovieDetailDS() {
        MovieBO expectedItem = businessObject;
        MovieDetailDS actualItem = MovieMapper.convert(expectedItem);

        assertEquals(expectedItem.getId(), actualItem.movieBasicDS.id);
        assertEquals(expectedItem.getTitle(), actualItem.movieBasicDS.title);
        assertEquals(expectedItem.getImage(), actualItem.movieBasicDS.image);
        assertEquals(expectedItem.getBackgroundImage(), actualItem.backgroundImage);
        assertEquals(expectedItem.getVoteCount(), actualItem.voteCount);
        assertEquals(expectedItem.getVoteAverage(), actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.getReleaseDate(), actualItem.releaseDate);
        assertEquals(expectedItem.getOverview(), actualItem.overview);
    }

    @Test
    public void convert_fromMovieDetailDS_toMovieBO() {
        MovieDetailDS expectedItem = dataStructure;
        MovieBO actualItem = MovieMapper.convert(expectedItem);

        assertEquals(expectedItem.movieBasicDS.id, actualItem.getId());
        assertEquals(expectedItem.movieBasicDS.title, actualItem.getTitle());
        assertEquals(expectedItem.movieBasicDS.image, actualItem.getImage());
        assertEquals(expectedItem.backgroundImage, actualItem.getBackgroundImage());
        assertEquals(expectedItem.voteCount, actualItem.getVoteCount());
        assertEquals(expectedItem.voteAverage, actualItem.getVoteAverage(), 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.getReleaseDate());
        assertEquals(expectedItem.overview, actualItem.getOverview());
    }

    @Test
    public void convert_fromMovieBOList_toMovieDSList() {
        List<MovieBO> expectedList = new ArrayList<>(Collections.singletonList(businessObject));
        List<MovieBasicDS> actualList = MovieMapper.convert(expectedList).list;

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).getId(), actualList.get(0).id);
        assertEquals(expectedList.get(0).getTitle(), actualList.get(0).title);
        assertEquals(expectedList.get(0).getImage(), actualList.get(0).image);
    }

}