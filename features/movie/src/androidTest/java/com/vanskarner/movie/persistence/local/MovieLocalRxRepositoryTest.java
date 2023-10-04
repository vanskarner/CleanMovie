package com.vanskarner.movie.persistence.local;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.vanskarner.core.concurrent.rxjava.TestRxFutureFactory;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;
import com.vanskarner.movie.businesslogic.MovieBasicDS;
import com.vanskarner.movie.businesslogic.MovieDetailDS;

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
    private MovieLocalRxRepository repository;
    private CompositeDisposable compositeDisposable;
    private TestRoomDB db;

    @Before
    public void setUp() {
        compositeDisposable = new CompositeDisposable();
        Scheduler testScheduler = Schedulers.trampoline();
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TestRoomDB.class).build();
        MovieDao dao = db.movieDetailDao();
        RxFutureFactory rxFutureFactory = TestRxFutureFactory
                .create(compositeDisposable, testScheduler, testScheduler);

        repository = new MovieLocalRxRepository(rxFutureFactory, dao);
    }

    @After
    public void tearDown() {
        compositeDisposable.clear();
        db.close();
    }

    @Test
    public void saveItem_savedItem() throws Exception {
        int expectedNumberItems = 1;
        MovieDetailDS item = createMovieBO(33);
        repository.saveMovie(item).await();
        int actualNumberItems = repository.getNumberMovies().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void getMovie_withValidId_returnItem() throws Exception {
        MovieDetailDS expectedItem = createMovieBO(33);
        repository.saveMovie(expectedItem).await();
        MovieDetailDS actualItem = repository.getMovie(expectedItem.basicInfo.id).get();

        assertEquals(expectedItem.basicInfo.id, actualItem.basicInfo.id);
        assertEquals(expectedItem.basicInfo.title, actualItem.basicInfo.title);
        assertEquals(expectedItem.basicInfo.image, actualItem.basicInfo.image);
        assertEquals(expectedItem.backgroundImage, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
    }

    @Test
    public void deleteMovie_withValidId_deletedItem() throws Exception {
        int expectedNumberItems = 0;
        int movieId = 33;
        repository.saveMovie(createMovieBO(movieId)).await();
        repository.deleteMovie(movieId).await();
        int actualNumberItems = repository.getNumberMovies().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void deleteAllMovies_withTwoItemsSaved_TwoItemsDeleted() throws Exception {
        int expectedNumberItems = 2;
        repository.saveMovie(createMovieBO(33)).await();
        repository.saveMovie(createMovieBO(34)).await();
        int actualNumberItems = repository.deleteAllMovies().get();

        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void getNumberMovies_withTwoItemsSaved_returnTwoItems() throws Exception {
        int expectedNumberItems = 2;
        repository.saveMovie(createMovieBO(33)).await();
        repository.saveMovie(createMovieBO(34)).await();
        int actualNumberItems = repository.getNumberMovies().get();

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

    private MovieDetailDS createMovieBO(int movieId) {
        return new MovieDetailDS(
                new MovieBasicDS(movieId,
                        "Clean Architecture",
                        "https://blog.cleancoder.com/anyImage.jpg"),
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