package com.vanskarner.movie.businesslogic;

import static org.junit.Assert.assertEquals;

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
        savedItem.basicInfo.id = 1;

        remoteRepository = new FakeRemoteRepository(Collections.singletonList(savedItem));
    }

    @Test
    public void getMovies_aSavedItem_returnOne() throws Exception {
        int expectedNumberItems = 1;
        List<MovieBasicDS> list = remoteRepository.getMovies(1).get().list;
        int actualNumberItems = list.size();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void getMovie_returnItem() throws Exception {
        MovieDetailDS expectedItem = savedItem;
        MovieDetailDS actualItem = remoteRepository.getMovie(savedItem.basicInfo.id).get();

        assertEquals(expectedItem.basicInfo.id, actualItem.basicInfo.id);
        assertEquals(expectedItem.basicInfo.title, actualItem.basicInfo.title);
        assertEquals(expectedItem.basicInfo.image, actualItem.basicInfo.image);
        assertEquals(expectedItem.backgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
    }

}