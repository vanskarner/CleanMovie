package com.vanskarner.movie.businesslogic.repository;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class FakeRemoteRepositoryTest {

    static FakeRemoteRepository remoteRepository;
    static MovieBO savedItem;

    @BeforeClass
    public static void setUp() {
        savedItem = new MovieBOBuilder()
                .withId(1)
                .build();

        remoteRepository = new FakeRemoteRepository(Collections.singletonList(savedItem));
    }

    @Test
    public void getMovies_aSavedItem_returnOne() throws Exception {
        List<MovieBO> list = remoteRepository.getMovies(1).get();
        int actualNumberItems = list.size();
        int expectedNumberItems = 1;

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void getMovie_returnItem() throws Exception {
        MovieBO actualItem = remoteRepository.getMovie(savedItem.getId()).get();
        MovieBO expectedItem = savedItem;

        assertEquals(expectedItem.getId(), actualItem.getId());
        assertEquals(expectedItem.getTitle(), actualItem.getTitle());
        assertEquals(expectedItem.getImage(), actualItem.getImage());
        assertEquals(expectedItem.getBackgroundImage(), actualItem.getBackgroundImage());
        assertEquals(expectedItem.getVoteCount(), actualItem.getVoteCount());
        assertEquals(expectedItem.getVoteAverage(), actualItem.getVoteAverage(), 0.01);
        assertEquals(expectedItem.getReleaseDate(), actualItem.getReleaseDate());
        assertEquals(expectedItem.getOverview(), actualItem.getOverview());
    }

}