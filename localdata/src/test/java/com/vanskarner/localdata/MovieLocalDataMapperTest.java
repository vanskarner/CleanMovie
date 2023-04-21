package com.vanskarner.localdata;

import org.junit.Test;

import static org.junit.Assert.*;

import com.vanskarner.entities.MovieBO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieLocalDataMapperTest {
    MovieEntity expectedEntity = new MovieEntity(
            1, "Clean Movie Entity", "Encoded Image",
            "Encoded Background Image", 100, 5.5f,
            "2023-01-29", "Any overview");
    MovieBO expectedBO = new MovieBO(
            2, "Clean Movie BO", "Encoded Image",
            "Encoded Background Image", 50, 7.5f,
            "2023-01-25", "Any overview");

    @Test
    public void convert_fromMovieDetailEntity_toMovieDetailBO() {
        MovieBO actualBO = MovieLocalDataMapper.convert(expectedEntity);

        assertEquals(expectedEntity.id, actualBO.getId());
        assertEquals(expectedEntity.title, actualBO.getTitle());
        assertEquals(expectedEntity.encodedImage, actualBO.getImage());
        assertEquals(expectedEntity.encodedBackgroundImage, actualBO.getBackgroundImage());
        assertEquals(expectedEntity.voteCount, actualBO.getVoteCount());
        assertEquals(expectedEntity.voteAverage, actualBO.getVoteAverage(), 0.01);
        assertEquals(expectedEntity.releaseDate, actualBO.getReleaseDate());
        assertEquals(expectedEntity.overview, actualBO.getOverview());
    }

    @Test
    public void convert_fromMovieDetailBO_toMovieDetailEntity() {
        MovieEntity actualEntity = MovieLocalDataMapper.convert(expectedBO);

        assertEquals(expectedBO.getId(), actualEntity.id);
        assertEquals(expectedBO.getTitle(), actualEntity.title);
        assertEquals(expectedBO.getImage(), actualEntity.encodedImage);
        assertEquals(expectedBO.getBackgroundImage(), actualEntity.encodedBackgroundImage);
        assertEquals(expectedBO.getVoteCount(), actualEntity.voteCount);
        assertEquals(expectedBO.getVoteAverage(), actualEntity.voteAverage, 0.01);
        assertEquals(expectedBO.getReleaseDate(), actualEntity.releaseDate);
        assertEquals(expectedBO.getOverview(), actualEntity.overview);
    }

    @Test
    public void convert_fromListMovieDetailEntity_toListMovieDetailBO() {
        List<MovieEntity> expectedEntity = new ArrayList<>(
                Collections.singletonList(this.expectedEntity));
        List<MovieBO> actualBO = MovieLocalDataMapper.convert(expectedEntity);

        assertEquals(expectedEntity.size(), actualBO.size());
        assertEquals(expectedEntity.get(0).id, actualBO.get(0).getId());
        assertEquals(expectedEntity.get(0).title, actualBO.get(0).getTitle());
        assertEquals(expectedEntity.get(0).encodedImage, actualBO.get(0).getImage());
        assertEquals(expectedEntity.get(0).encodedBackgroundImage, actualBO.get(0).getBackgroundImage());
        assertEquals(expectedEntity.get(0).voteCount, actualBO.get(0).getVoteCount());
        assertEquals(expectedEntity.get(0).voteAverage, actualBO.get(0).getVoteAverage(), 0.01);
        assertEquals(expectedEntity.get(0).releaseDate, actualBO.get(0).getReleaseDate());
        assertEquals(expectedEntity.get(0).overview, actualBO.get(0).getOverview());
    }

}