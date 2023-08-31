package com.vanskarner.movie.persistence.local;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;

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
        persistenceLayerDataStructure = new MovieEntity(1,
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
                2,
                "Clean Architecture",
                "Encoded_Image",
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
        MovieDetailDS actualItem = MovieLocalDataMapper.convert(persistenceLayerDataStructure);
        MovieEntity expectedItem = persistenceLayerDataStructure;

        assertEquals(expectedItem.id, actualItem.id);
        assertEquals(expectedItem.title, actualItem.title);
        assertEquals(expectedItem.encodedImage, actualItem.image);
        assertEquals(expectedItem.encodedBackgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
    }

    @Test
    public void convert_fromMovieDetailDS_toMovieEntity() {
        MovieEntity actualItem = MovieLocalDataMapper.convert(businessLogicDataStructure);
        MovieDetailDS expectedItem = businessLogicDataStructure;

        assertEquals(expectedItem.id, actualItem.id);
        assertEquals(expectedItem.title, actualItem.title);
        assertEquals(expectedItem.image, actualItem.encodedImage);
        assertEquals(expectedItem.backgroundImage, actualItem.encodedBackgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
    }

    @Test
    public void convert_fromListMovieEntity_toListMovieDS() {
        List<MovieEntity> expectedList =
                new ArrayList<>(Collections.singletonList(persistenceLayerDataStructure));
        List<MovieDS> actualList = MovieLocalDataMapper.convert(expectedList).list;

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).id, actualList.get(0).id);
        assertEquals(expectedList.get(0).title, actualList.get(0).title);
        assertEquals(expectedList.get(0).encodedImage, actualList.get(0).image);
    }

}