package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MovieBOTest {

    @Test
    public void isRecommended_voteCountAverageVoteAreSuperior_recommended() {
        MovieBO movieBO = new MovieBO(76,7.6f);

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreExact_recommended() {
        MovieBO movieBO = new MovieBO(75,7.5f);

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountIsInferior_notRecommended() {
        MovieBO movieBO = new MovieBO(74,7.5f);

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreInferior_notRecommended() {
        MovieBO movieBO = new MovieBO(74,7.4f);

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteAverageIsInferior_notRecommended() {
        MovieBO movieBO = new MovieBO(75,7.4f);

        assertFalse(movieBO.isRecommended());
    }

}