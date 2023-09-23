package com.vanskarner.movie.persistence.local;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.MovieBasicDS;
import com.vanskarner.movie.MovieDetailDS;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieLocalDataMapperTest {
    static MovieEntity persistenceLayerDataStructure;
    static MovieDetailDS businessLogicDataStructure;

    @BeforeClass
    public static void setup() {
        persistenceLayerDataStructure = new MovieEntity(
                1,
                "Clean Architecture",
                "Encoded_Image",
                "Encoded_Background_Image",
                100,
                9.5f,
                "2023-08-15",
                "Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time.");
        businessLogicDataStructure = new MovieDetailDS(
                new MovieBasicDS(
                        2,
                        "Clean Architecture",
                        "Encoded_Image"
                ),
                "Encoded_Background_Image",
                100,
                9.5f,
                "2023-08-15",
                "Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time."
        );
    }

    @Test
    public void convert_fromMovieEntity_toMovieDetailDS() {
        MovieEntity expectedItem = persistenceLayerDataStructure;
        MovieDetailDS actualItem = MovieLocalDataMapper.convert(persistenceLayerDataStructure);

        assertEquals(expectedItem.id, actualItem.basicInfo.id);
        assertEquals(expectedItem.title, actualItem.basicInfo.title);
        assertEquals(expectedItem.encodedImage, actualItem.basicInfo.image);
        assertEquals(expectedItem.encodedBackgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
    }

    @Test
    public void convert_fromMovieDetailDS_toMovieEntity() {
        MovieDetailDS expectedItem = businessLogicDataStructure;
        MovieEntity actualItem = MovieLocalDataMapper.convert(businessLogicDataStructure);

        assertEquals(expectedItem.basicInfo.id, actualItem.id);
        assertEquals(expectedItem.basicInfo.title, actualItem.title);
        assertEquals(expectedItem.basicInfo.image, actualItem.encodedImage);
        assertEquals(expectedItem.backgroundImage, actualItem.encodedBackgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
    }

    @Test
    public void convert_fromMovieEntityList_toMoviesDS() {
        List<MovieEntity> expectedList =
                new ArrayList<>(Collections.singletonList(persistenceLayerDataStructure));
        List<MovieBasicDS> actualList = MovieLocalDataMapper.convert(expectedList).list;

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).id, actualList.get(0).id);
        assertEquals(expectedList.get(0).title, actualList.get(0).title);
        assertEquals(expectedList.get(0).encodedImage, actualList.get(0).image);
    }

}