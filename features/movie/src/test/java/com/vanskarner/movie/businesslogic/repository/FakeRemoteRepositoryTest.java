package com.vanskarner.movie.businesslogic.repository;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class FakeRemoteRepositoryTest {

    static FakeRemoteRepository remoteRepository;
    static MovieDetailDS savedItem;

    @BeforeClass
    public static void setUp() {
        savedItem = MovieDetailDS.empty();
        savedItem.id = 1;

        remoteRepository = new FakeRemoteRepository(Collections.singletonList(savedItem));
    }

    @Test
    public void getMovies_aSavedItem_returnOne() throws Exception {
        List<MovieDS> list = remoteRepository.getMovies(1).get().list;
        int actualNumberItems = list.size();
        int expectedNumberItems = 1;

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void getMovie_returnItem() throws Exception {
        MovieDetailDS actualItem = remoteRepository.getMovie(savedItem.id).get();
        MovieDetailDS expectedItem = savedItem;

        assertEquals(expectedItem.id, actualItem.id);
        assertEquals(expectedItem.title, actualItem.title);
        assertEquals(expectedItem.image, actualItem.image);
        assertEquals(expectedItem.backgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
    }

}