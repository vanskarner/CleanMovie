package com.vanskarner.usecases.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        MovieBO item = new MovieBOBuilder()
                .withId(1)
                .build();
        localRepository.saveMovie(item).await();
        List<MovieBO> list = localRepository.getMovies().get();
        int actualNumberItems = list.size();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void getMovie_returnItem() throws Exception {
        MovieBO expectedItem = new MovieBOBuilder()
                .withId(1)
                .build();
        localRepository.saveMovie(expectedItem).await();
        MovieBO actualItem = localRepository.getMovie(expectedItem.getId()).get();

        assertEquals(expectedItem.getId(), actualItem.getId());
        assertEquals(expectedItem.getTitle(), actualItem.getTitle());
        assertEquals(expectedItem.getImage(), actualItem.getImage());
        assertEquals(expectedItem.getBackgroundImage(), actualItem.getBackgroundImage());
        assertEquals(expectedItem.getVoteCount(), actualItem.getVoteCount());
        assertEquals(expectedItem.getVoteAverage(), actualItem.getVoteAverage(), 0.01);
        assertEquals(expectedItem.getReleaseDate(), actualItem.getReleaseDate());
        assertEquals(expectedItem.getOverview(), actualItem.getOverview());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteMovie_getMovieRemoved_throwNoSuchElementException() throws Exception {
        MovieBO item = new MovieBOBuilder()
                .withId(1)
                .build();
        localRepository.saveMovie(item).await();
        localRepository.deleteMovie(item.getId()).await();
        localRepository.getMovie(item.getId()).get();
    }

    @Test
    public void deleteAllMovies_withASavedItem_returnOne() throws Exception {
        int expectedNumberItemsDeleted = 1;
        MovieBO firstItem = new MovieBOBuilder()
                .withId(1)
                .build();
        localRepository.saveMovie(firstItem).await();
        int actualNumberItemsDeleted = localRepository.deleteAllMovies().get();

        assertEquals(expectedNumberItemsDeleted, actualNumberItemsDeleted);
    }

    @Test
    public void getNumberMovies_withASavedItem_returnOne() throws Exception {
        int expectedNumberItemsDeleted = 1;
        MovieBO item = new MovieBOBuilder()
                .withId(1)
                .build();
        localRepository.saveMovie(item).await();
        int actualNumberItemsDeleted = localRepository.getNumberMovies().get();

        assertEquals(expectedNumberItemsDeleted, actualNumberItemsDeleted);
    }

    @Test
    public void checkMovie_withValidId_itemExists() throws Exception {
        MovieBO item = new MovieBOBuilder()
                .withId(1)
                .build();
        localRepository.saveMovie(item).await();
        boolean exists = localRepository.checkMovie(item.getId()).get();

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
        MovieBO item = new MovieBOBuilder()
                .withId(1)
                .build();
        localRepository.saveMovie(item).await();
        int actualNumberItems = localRepository.getNumberMovies().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

}