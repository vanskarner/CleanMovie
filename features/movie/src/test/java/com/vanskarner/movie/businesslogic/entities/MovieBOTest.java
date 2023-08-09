package com.vanskarner.movie.businesslogic.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MovieBOTest {
    static int counter = 0;

    @Test
    public void isRecommended_voteCountAverageVoteAreSuperior_true() {
        MovieBO movieBO = createMovie(76, 7.6f);

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreExact_true() {
        MovieBO movieBO = createMovie(75, 7.5f);

        assertTrue(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountIsInferior_false() {
        MovieBO movieBO = createMovie(74, 7.5f);

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteCountAverageVoteAreInferior_false() {
        MovieBO movieBO = createMovie(74, 7.4f);

        assertFalse(movieBO.isRecommended());
    }

    @Test
    public void isRecommended_voteAverageIsInferior_false() {
        MovieBO movieBO = createMovie(75, 7.4f);

        assertFalse(movieBO.isRecommended());
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