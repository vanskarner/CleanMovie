package com.vanskarner.movie.businesslogic.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MovieBOTest {
    static int counter = 0;

    @Test
    public void isRecommended_voteAverageIsInferior_false() {
        int voteCount = 100;
        float voteAverage = 7.4f;
        MovieBO movieBO = createMovie(voteCount, voteAverage);

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountIsInferior_false() {
        int voteCount = 74;
        float voteAverage = 10;
        MovieBO movieBO = createMovie(voteCount, voteAverage);

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreSuperior_true() {
        int voteCount = 76;
        float voteAverage = 7.5f;
        MovieBO movieBO = createMovie(voteCount, voteAverage);

        assertTrue(movieBO.isRecommended());
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