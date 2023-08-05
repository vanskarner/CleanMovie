package com.vanskarner.cleanmovie.features.movie.favorites;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.vanskarner.cleanmovie.utils.CustomMatcher.withRecyclerViewItemCount;

import android.os.Bundle;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.vanskarner.cleanmovie.DataBindingIdlingResource;
import com.vanskarner.cleanmovie.TestApp;
import com.vanskarner.cleanmovie.utils.FileUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.vanskarner.cleanmovie.R;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;

import java.io.IOException;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class FavoritesFragmentTest {
    DataBindingIdlingResource dataBindingIdlingResource = new DataBindingIdlingResource();
    @Inject
    MovieServices movieServices;

    @Before
    public void setUp() {
        TestApp testApp = (TestApp) InstrumentationRegistry.getInstrumentation()
                .getTargetContext()
                .getApplicationContext();
        testApp.getComponent().inject(this);
        IdlingRegistry.getInstance().register(dataBindingIdlingResource);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource);
        movieServices.deleteAllFavorite().get();
    }

    @Test
    public void deleteAllFavorites_showMessageNoFavorites() throws IOException {
        String base64TopicImage = FileUtils.readFile("base64_topic_image.txt");
        MovieDetailDS detailDS = new MovieDetailDS(1, "Clean Architecture",
                base64TopicImage, base64TopicImage, 100, 8.5f,
                "2023-03-01", "Separation of responsibilities");
        movieServices.actionFavorite(detailDS).get();
        FragmentScenario<FavoritesFragment> scenario = FragmentScenario.launchInContainer(
                FavoritesFragment.class, Bundle.EMPTY, R.style.Theme_CleanMovie);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.deleteMenuItem)).perform(click());
        onView(withText(R.string.ok)).perform(click());
        onView(withId(R.id.favoritesRecycler))
                .check(matches(withRecyclerViewItemCount(0)));
        onView(withId(R.id.noFavoritesView)).check(matches(isDisplayed()));
        scenario.close();
    }

    @Test
    public void selectFavoriteItem_showDetailDialog() throws IOException {
        String base64TopicImage = FileUtils.readFile("base64_topic_image.txt");
        MovieDetailDS detailDS = new MovieDetailDS(1, "Clean Architecture",
                base64TopicImage, base64TopicImage, 100, 8.5f,
                "2023-03-01", "Separation of responsibilities");
        movieServices.actionFavorite(detailDS).get();
        FragmentScenario<FavoritesFragment> scenario = FragmentScenario.launchInContainer(
                FavoritesFragment.class, Bundle.EMPTY, R.style.Theme_CleanMovie);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.favoritesRecycler))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.containerFavoriteDetail))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withId(R.id.title)).check(matches(withText(detailDS.title)));
        onView(withId(R.id.overview)).check(matches(withText(detailDS.overview)));
        onView(withId(R.id.releaseDate)).check(matches(withText(detailDS.releaseDate)));
        onView(withId(R.id.tvVoteAverage))
                .check(matches(withText(String.valueOf(detailDS.voteAverage))));
        scenario.close();
    }

}