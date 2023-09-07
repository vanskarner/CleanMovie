package com.vanskarner.localdata;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import com.vanskarner.entities.MovieBO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieLocalDataMapperTest {
    static MovieEntity dataStructure;
    static MovieBO businessObject;

    @BeforeClass
    public static void setupClass() {
        dataStructure = new MovieEntity(1,
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
        businessObject = new MovieBO(1,
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
    }

    @Test
    public void convert_fromMovieEntity_toMovieBO() {
        MovieEntity expectedItem = dataStructure;
        MovieBO actualItem = MovieLocalDataMapper.convert(dataStructure);

        assertEquals(expectedItem.id, actualItem.getId());
        assertEquals(expectedItem.title, actualItem.getTitle());
        assertEquals(expectedItem.encodedImage, actualItem.getImage());
        assertEquals(expectedItem.encodedBackgroundImage, actualItem.getBackgroundImage());
        assertEquals(expectedItem.voteCount, actualItem.getVoteCount());
        assertEquals(expectedItem.voteAverage, actualItem.getVoteAverage(), 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.getReleaseDate());
        assertEquals(expectedItem.overview, actualItem.getOverview());
    }

    @Test
    public void convert_fromMovieBO_toMovieEntity() {
        MovieBO expectedItem = businessObject;
        MovieEntity actualItem = MovieLocalDataMapper.convert(businessObject);

        assertEquals(expectedItem.getId(), actualItem.id);
        assertEquals(expectedItem.getTitle(), actualItem.title);
        assertEquals(expectedItem.getImage(), actualItem.encodedImage);
        assertEquals(expectedItem.getBackgroundImage(), actualItem.encodedBackgroundImage);
        assertEquals(expectedItem.getVoteCount(), actualItem.voteCount);
        assertEquals(expectedItem.getVoteAverage(), actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.getReleaseDate(), actualItem.releaseDate);
        assertEquals(expectedItem.getOverview(), actualItem.overview);
    }

    @Test
    public void convert_fromMovieEntityList_toMovieBOList() {
        List<MovieEntity> expectedList = new ArrayList<>(Collections.singletonList(dataStructure));
        List<MovieBO> actualList = MovieLocalDataMapper.convert(expectedList);

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).id, actualList.get(0).getId());
        assertEquals(expectedList.get(0).title, actualList.get(0).getTitle());
        assertEquals(expectedList.get(0).encodedImage, actualList.get(0).getImage());
        assertEquals(expectedList.get(0).encodedBackgroundImage, actualList.get(0).getBackgroundImage());
        assertEquals(expectedList.get(0).voteCount, actualList.get(0).getVoteCount());
        assertEquals(expectedList.get(0).voteAverage, actualList.get(0).getVoteAverage(), 0.01);
        assertEquals(expectedList.get(0).releaseDate, actualList.get(0).getReleaseDate());
        assertEquals(expectedList.get(0).overview, actualList.get(0).getOverview());
    }

}