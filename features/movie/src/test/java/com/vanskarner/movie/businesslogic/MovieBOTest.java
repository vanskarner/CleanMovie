package com.vanskarner.movie.businesslogic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MovieBOTest {

    @Test
    public void isRecommended_voteCountAverageVoteAreSuperior_recommended() {
        MovieBO movieBO = new MovieBOBuilder()
                .withVoteCount(76)
                .withVoteAverage(7.6f)
                .build();

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreExact_recommended() {
        MovieBO movieBO = new MovieBOBuilder()
                .withVoteCount(75)
                .withVoteAverage(7.5f)
                .build();

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountIsInferior_notRecommended() {
        MovieBO movieBO = new MovieBOBuilder()
                .withVoteCount(74)
                .withVoteAverage(7.5f)
                .build();

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreInferior_notRecommended() {
        MovieBO movieBO = new MovieBOBuilder()
                .withVoteCount(74)
                .withVoteAverage(7.4f)
                .build();

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteAverageIsInferior_notRecommended() {
        MovieBO movieBO = new MovieBOBuilder()
                .withVoteCount(75)
                .withVoteAverage(7.4f)
                .build();

        assertFalse(movieBO.isRecommended());
    }

}