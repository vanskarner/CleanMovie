package com.vanskarner.movie.businesslogic.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MovieBOTest {
    static int counter = 0;

    @Test
    public void calculateRecommendation_OnlyVoteAverageIsInferior_false() {
        int voteCount = 100;
        float voteAverage = 7.4f;
        MovieBO movieBO = createMovie(voteCount, voteAverage);

        assertFalse(movieBO.calculateRecommendation());
    }

    @Test
    public void calculateRecommendation_OnlyVoteCountIsInferior_false() {
        int voteCount = 74;
        float voteAverage = 10;
        MovieBO movieBO = createMovie(voteCount, voteAverage);

        assertFalse(movieBO.calculateRecommendation());
    }

    @Test
    public void calculateRecommendation_voteCountAverageVoteAreSuperior_true() {
        int voteCount = 76;
        float voteAverage = 7.5f;
        MovieBO movieBO = createMovie(voteCount, voteAverage);

        assertTrue(movieBO.calculateRecommendation());
    }

    private MovieBO createMovie(int voteCount, float voteAverage) {
        int uniqueId = getUniqueInt();
        return new MovieBO(
                uniqueId,
                "Title" + uniqueId,
                "Image" + uniqueId,
                "BackgroundImage" + uniqueId,
                voteCount,
                voteAverage,
                "ReleaseData" + uniqueId,
                "Overview" + uniqueId);
    }

    private int getUniqueInt() {
        counter++;
        return counter;
    }
}