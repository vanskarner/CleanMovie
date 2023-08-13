package com.vanskarner.movie.businesslogic.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.MovieBOBuilder;

import org.junit.Test;

public class MovieBOTest {

    @Test
    public void isRecommended_voteCountAverageVoteAreSuperior_true() {
        MovieBO movieBO = MovieBOBuilder.getInstance()
                .withVoteCount(76)
                .withVoteAverage(7.6f)
                .build();

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreExact_true() {
        MovieBO movieBO = MovieBOBuilder.getInstance()
                .withVoteCount(75)
                .withVoteAverage(7.5f)
                .build();

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountIsInferior_false() {
        MovieBO movieBO = MovieBOBuilder.getInstance()
                .withVoteCount(74)
                .withVoteAverage(7.5f)
                .build();

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreInferior_false() {
        MovieBO movieBO = MovieBOBuilder.getInstance()
                .withVoteCount(74)
                .withVoteAverage(7.4f)
                .build();

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteAverageIsInferior_false() {
        MovieBO movieBO = MovieBOBuilder.getInstance()
                .withVoteCount(75)
                .withVoteAverage(7.4f)
                .build();

        assertFalse(movieBO.isRecommended());
    }

}