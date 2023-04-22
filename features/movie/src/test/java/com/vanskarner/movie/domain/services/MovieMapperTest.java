package com.vanskarner.movie.domain.services;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.domain.ds.MovieDetailDS;
import com.vanskarner.movie.domain.ds.MoviesDS;
import com.vanskarner.movie.domain.entities.MovieBO;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieMapperTest {
    MovieBO expectedBO = new MovieBO(
            1, "Clean movie BO", "any image", "any background image",
            75, 7.4f, "2023-01-12", "My overview"
    );
    MovieDetailDS expectedDS = new MovieDetailDS(2, "Clean Movie DS", "other image",
            "other background image", 60, 7.8f,
            "2023-01-13", "Other overview");

    @Test
    public void convert_fromMovieDetailBO_toMovieDetailDS() {
        MovieDetailDS actualDS = MovieMapper.convert(expectedBO);

        assertEquals(expectedBO.getId(), actualDS.id);
        assertEquals(expectedBO.getTitle(), actualDS.title);
        assertEquals(expectedBO.getImage(), actualDS.image);
        assertEquals(expectedBO.getBackgroundImage(), actualDS.backgroundImage);
        assertEquals(expectedBO.getVoteCount(), actualDS.voteCount);
        assertEquals(expectedBO.getVoteAverage(), actualDS.voteAverage, 0.01);
        assertEquals(expectedBO.getReleaseDate(), actualDS.releaseDate);
        assertEquals(expectedBO.getOverview(), actualDS.overview);
    }

    @Test
    public void convert_fromMovieDetailDS_toMovieDetailBO() {
        MovieBO actualBO = MovieMapper.convert(expectedDS);

        assertEquals(expectedDS.id, actualBO.getId());
        assertEquals(expectedDS.title, actualBO.getTitle());
        assertEquals(expectedDS.image, actualBO.getImage());
        assertEquals(expectedDS.backgroundImage, actualBO.getBackgroundImage());
        assertEquals(expectedDS.voteCount, actualBO.getVoteCount());
        assertEquals(expectedDS.voteAverage, actualBO.getVoteAverage(), 0.01);
        assertEquals(expectedDS.releaseDate, actualBO.getReleaseDate());
        assertEquals(expectedDS.overview, actualBO.getOverview());
    }

    @Test
    public void convert_fromListMovieDetailBO_toListMoviesDS() {
        List<MovieBO> expectedBO = new ArrayList<>(Collections.singletonList(this.expectedBO));
        MoviesDS actualDS = MovieMapper.convert(expectedBO);

        assertEquals(expectedBO.size(), actualDS.list.size());
        assertEquals(expectedBO.get(0).getId(), actualDS.list.get(0).id);
        assertEquals(expectedBO.get(0).getTitle(), actualDS.list.get(0).title);
        assertEquals(expectedBO.get(0).getImage(), actualDS.list.get(0).image);
    }
}