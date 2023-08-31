package com.vanskarner.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MovieBOTest {

    @Test
    public void isRecommended_voteCountAverageVoteAreSuperior_recommended() {
        MovieBO movieBO = createMovieBO(76, 7.6f);

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreExact_recommended() {
        MovieBO movieBO = createMovieBO(75, 7.5f);

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountIsInferior_notRecommended() {
        MovieBO movieBO = createMovieBO(74, 7.5f);

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreInferior_notRecommended() {
        MovieBO movieBO = createMovieBO(74, 7.4f);

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteAverageIsInferior_notRecommended() {
        MovieBO movieBO = createMovieBO(75, 7.4f);

        assertFalse(movieBO.isRecommended());
    }

    private MovieBO createMovieBO(int voteCount, float voteAverage) {
        return new MovieBO(0, "", "", "", voteCount, voteAverage,
                "", "");
    }

}