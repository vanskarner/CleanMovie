package com.vanskarner.movie.persistence.local;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.vanskarner.core.concurrent.rxjava.DefaultRxFutureFactory;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.main.TestRoomDB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MovieLocalRxRepositoryTest {
    MovieLocalRxRepository repository;
    CompositeDisposable compositeDisposable;
    TestRoomDB db;

    @Before
    public void setUp() {
        compositeDisposable = new CompositeDisposable();
        Scheduler testScheduler = Schedulers.trampoline();
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TestRoomDB.class).build();
        MovieDao dao = db.movieDetailDao();
        RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable,
                testScheduler, testScheduler);

        repository = new MovieLocalRxRepository(rxFutureFactory, dao);
    }

    @After
    public void tearDown() {
        compositeDisposable.clear();
        db.close();
    }

    @Test
    public void saveItem_savedItem() throws Exception {
        MovieBO item = createMovieBO(33);
        repository.saveMovie(item).await();
        int actualNumberItems = repository.getNumberMovies().get();
        int expectedNumberItems = 1;

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void getMovie_withValidId_returnItem() throws Exception {
        MovieBO expectedItem = createMovieBO(33);
        repository.saveMovie(expectedItem).await();
        MovieBO actualItem = repository.getMovie(expectedItem.getId()).get();

        assertEquals(expectedItem.getId(), actualItem.getId());
        assertEquals(expectedItem.getTitle(), actualItem.getTitle());
        assertEquals(expectedItem.getImage(), actualItem.getImage());
        assertEquals(expectedItem.getBackgroundImage(), actualItem.getBackgroundImage());
        assertEquals(expectedItem.getVoteCount(), actualItem.getVoteCount());
        assertEquals(expectedItem.getVoteAverage(), actualItem.getVoteAverage(), 0.01);
        assertEquals(expectedItem.getReleaseDate(), actualItem.getReleaseDate());
        assertEquals(expectedItem.getOverview(), actualItem.getOverview());
    }

    @Test
    public void deleteMovie_withValidId_deletedItem() throws Exception {
        int movieId = 33;
        repository.saveMovie(createMovieBO(movieId)).await();
        repository.deleteMovie(movieId).await();
        int actualNumberItems = repository.getNumberMovies().get();
        int expectedNumberItems = 0;

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void deleteAllMovies_withTwoItemsSaved_TwoItemsDeleted() throws Exception {
        repository.saveMovie(createMovieBO(33)).await();
        repository.saveMovie(createMovieBO(34)).await();
        int actualNumberItems = repository.deleteAllMovies().get();
        int expectedNumberItems = 2;

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void getNumberMovies_withTwoItemsSaved_returnTwoItems() throws Exception {
        repository.saveMovie(createMovieBO(33)).await();
        repository.saveMovie(createMovieBO(34)).await();
        int actualNumberItems = repository.getNumberMovies().get();
        int expectedNumberItems = 2;

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void checkMovie_withValidID_itemExists() throws Exception {
        int validID = 33;
        repository.saveMovie(createMovieBO(validID)).await();
        boolean exists = repository.checkMovie(validID).get();

        assertTrue(exists);
    }

    @Test
    public void checkMovie_withInvalidID_itemNotExist() throws Exception {
        boolean exists = repository.checkMovie(33).get();

        assertFalse(exists);
    }

    private MovieBO createMovieBO(int movieId) {
        return new MovieBO(movieId,
                "Clean Architecture",
                "https://blog.cleancoder.com/anyImage.jpg",
                "https://blog.cleancoder.com/anyBackImage.jpg",
                100,
                9.5f,
                "023-08-15",
                "Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time.");
    }

}