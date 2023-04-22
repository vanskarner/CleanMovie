package com.vanskarner.movie.data.local;

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
import com.vanskarner.movie.domain.entities.MovieBO;
import com.vanskarner.movie.domain.repository.MovieLocalRepository;

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
    MovieLocalRepository repository;
    CompositeDisposable compositeDisposable;
    TestRoomDB db;

    @Before
    public void setUp() {
        compositeDisposable = new CompositeDisposable();
        Scheduler testScheduler = Schedulers.trampoline();
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TestRoomDB.class).build();
        MovieDao dao = db.movieDetailDao();
        RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable, testScheduler, testScheduler);

        repository = new MovieLocalRxRepository(rxFutureFactory, dao);
    }

    @After
    public void tearDown() {
        compositeDisposable.clear();
        db.close();
    }

    @Test
    public void saveItems_returnQuantityItems() {
        int expected = 2;
        repository.saveMovie(getItem(33)).await();
        repository.saveMovie(getItem(34)).await();
        int actual = repository.getMovies().get().size();

        assertEquals(expected, actual);
    }

    @Test
    public void saveItem_returnItem() {
        int movieId = 33;
        MovieBO expected = getItem(movieId);
        repository.saveMovie(expected).await();
        MovieBO actual = repository.getMovie(movieId).get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getImage(), actual.getImage());
        assertEquals(expected.getBackgroundImage(), actual.getBackgroundImage());
        assertEquals(expected.getVoteCount(), actual.getVoteCount());
        assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01);
        assertEquals(expected.getReleaseDate(), actual.getReleaseDate());
        assertEquals(expected.getOverview(), actual.getOverview());
    }

    @Test
    public void deleteMovie_complete() {
        int movieId = 33;
        repository.saveMovie(getItem(movieId)).await();
        repository.deleteMovie(movieId).await();
        int actual = repository.getNumberMovies().get();

        assertEquals(0, actual);
    }

    @Test
    public void deleteAllMovies_returnNumberOfDeletedItems() {
        int expected = 2;
        repository.saveMovie(getItem(33)).await();
        repository.saveMovie(getItem(34)).await();
        int actual = repository.deleteAllMovies().get();

        assertEquals(expected, actual);
    }

    @Test
    public void getNumberMovies_returnNumberOfItems() {
        int expected = 2;
        repository.saveMovie(getItem(33)).await();
        repository.saveMovie(getItem(34)).await();
        int actual = repository.getNumberMovies().get();

        assertEquals(expected, actual);
    }

    @Test
    public void checkMovie_validID_returnTrue() {
        int validID = 33;
        repository.saveMovie(getItem(validID)).await();
        boolean value = repository.checkMovie(validID).get();

        assertTrue(value);
    }

    @Test
    public void checkMovie_invalidID_returnFalse() {
        boolean value = repository.checkMovie(33).get();

        assertFalse(value);
    }

    private MovieBO getItem(int movieId) {
        return new MovieBO(movieId, "Clean Movie", "Any Image",
                "Any background image",
                100, 9.8f, "2023-01-30", "Any overview");
    }

}