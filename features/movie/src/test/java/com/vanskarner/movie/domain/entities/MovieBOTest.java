package com.vanskarner.movie.domain.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MovieBOTest {

    @Test
    public void calculateRecommendation_superiorVoteCountInferiorVoteAverage_false() {
        int voteCount = 100;
        float voteAverage = 7.4f;
        MovieBO movieBO = getMovieDetailBO(voteCount, voteAverage);

        assertFalse(movieBO.calculateRecommendation());
    }

    @Test
    public void calculateRecommendation_inferiorVoteCountSuperiorVoteAverage_false() {
        int voteCount = 74;
        float voteAverage = 10;
        MovieBO movieBO = getMovieDetailBO(voteCount, voteAverage);

        assertFalse(movieBO.calculateRecommendation());
    }

    @Test
    public void calculateRecommendation_superiorVoteCountSuperiorVoteAverage_true() {
        int voteCount = 76;
        float voteAverage = 7.5f;
        MovieBO movieBO = getMovieDetailBO(voteCount, voteAverage);

        assertTrue(movieBO.calculateRecommendation());
    }

    private MovieBO getMovieDetailBO(int voteCount, float voteAverage) {
        return new MovieBO(0, "", "", "", voteCount, voteAverage,
                "", "");
    }

}