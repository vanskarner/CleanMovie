package com.vanskarner.movie.businesslogic.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

public class FakeLocalRepositoryTest {

    FakeLocalRepository localRepository;

    @Before
    public void setUp() {
        localRepository = new FakeLocalRepository();
    }

    @Test
    public void getMovies_aSavedItem_returnOne() throws Exception {
        int expectedNumberItems = 1;
        MovieDetailDS item = MovieDetailDS.empty();
        item.id = 1;
        localRepository.saveMovie(item).await();
        List<MovieDS> list = localRepository.getMovies().get().list;
        int actualNumberItems = list.size();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void getMovie_returnItem() throws Exception {
        MovieDetailDS expectedItem = MovieDetailDS.empty();
        expectedItem.id = 1;
        localRepository.saveMovie(expectedItem).await();
        MovieDetailDS actualItem = localRepository.getMovie(expectedItem.id).get();

        assertEquals(expectedItem.id, actualItem.id);
        assertEquals(expectedItem.title, actualItem.title);
        assertEquals(expectedItem.image, actualItem.image);
        assertEquals(expectedItem.backgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteMovie_getMovieRemoved_throwNoSuchElementException() throws Exception {
        MovieDetailDS item = MovieDetailDS.empty();
        item.id = 1;
        localRepository.saveMovie(item).await();
        localRepository.deleteMovie(item.id).await();
        localRepository.getMovie(item.id).get();
    }

    @Test
    public void deleteAllMovies_withASavedItem_returnOne() throws Exception {
        int expectedNumberItemsDeleted = 1;
        MovieDetailDS firstItem = MovieDetailDS.empty();
        firstItem.id = 1;
        localRepository.saveMovie(firstItem).await();
        int actualNumberItemsDeleted = localRepository.deleteAllMovies().get();

        assertEquals(expectedNumberItemsDeleted, actualNumberItemsDeleted);
    }

    @Test
    public void getNumberMovies_withASavedItem_returnOne() throws Exception {
        int expectedNumberItemsDeleted = 1;
        MovieDetailDS item = MovieDetailDS.empty();
        item.id = 1;
        localRepository.saveMovie(item).await();
        int actualNumberItemsDeleted = localRepository.getNumberMovies().get();

        assertEquals(expectedNumberItemsDeleted, actualNumberItemsDeleted);
    }

    @Test
    public void checkMovie_withValidId_itemExists() throws Exception {
        MovieDetailDS item = MovieDetailDS.empty();
        item.id = 1;
        localRepository.saveMovie(item).await();
        boolean exists = localRepository.checkMovie(item.id).get();

        assertTrue(exists);
    }

    @Test
    public void checkMovie_withInvalidId_itemNotExists() throws Exception {
        boolean exists = localRepository.checkMovie(1).get();

        assertFalse(exists);
    }

    @Test
    public void saveMovie_savedItem() throws Exception {
        int expectedNumberItems = 1;
        MovieDetailDS item = MovieDetailDS.empty();
        item.id = 1;
        localRepository.saveMovie(item).await();
        int actualNumberItems = localRepository.getNumberMovies().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

}