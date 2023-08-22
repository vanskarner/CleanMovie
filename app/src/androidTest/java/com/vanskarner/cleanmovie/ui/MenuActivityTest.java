package com.vanskarner.cleanmovie.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.vanskarner.cleanmovie.utils.CustomMatcher.withActionIconDrawable;
import static com.vanskarner.cleanmovie.utils.CustomMatcher.withImageDrawable;
import static com.vanskarner.cleanmovie.utils.CustomMatcher.withRecyclerViewItemCount;

import android.view.KeyEvent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.vanskarner.cleanmovie.utils.DataBindingIdlingResource;
import com.vanskarner.cleanmovie.main.TestApp;
import com.vanskarner.cleanmovie.utils.TestMockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.vanskarner.cleanmovie.R;
import com.vanskarner.movie.businesslogic.services.MovieServices;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.inject.Inject;

import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MenuActivityTest {
    MockWebServer server = new MockWebServer();
    TestMockWebServer testMockWebServer = new TestMockWebServer(server);
    DataBindingIdlingResource dataBindingIdlingResource = new DataBindingIdlingResource();
    @Inject
    MovieServices movieServices;

    @Before
    public void setUp() throws IOException {
        server.start(8080);
        TestApp testApp = (TestApp) InstrumentationRegistry.getInstrumentation()
                .getTargetContext()
                .getApplicationContext();
        testApp.getComponent().inject(this);
        IdlingRegistry.getInstance().register(dataBindingIdlingResource);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource);
        movieServices.deleteAllFavorite().get();
    }

    @Test
    public void saveFavorite_showMarkedAsFavorite() throws IOException {
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        ActivityScenario<MenuActivity> scenario = ActivityScenario.launch(MenuActivity.class);
        dataBindingIdlingResource.monitorActivity(scenario);

        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favoriteMenuItem))
                .check(matches(withActionIconDrawable(R.drawable.ic_favorite_border_24)));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(withId(R.id.favoritesNav)).perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favoriteMenuItem))
                .check(matches(withActionIconDrawable(R.drawable.ic_favorite_24)));
        scenario.close();
    }

    @Test
    public void deleteAllFavorites_showThemAsNotFavorites() throws IOException {
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_2.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_2.json");
        ActivityScenario<MenuActivity> scenario = ActivityScenario.launch(MenuActivity.class);
        dataBindingIdlingResource.monitorActivity(scenario);

        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(withId(R.id.favoritesNav)).perform(click());
        onView(withId(R.id.deleteMenuItem)).perform(click());
        onView(withText(R.string.ok)).perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favoriteMenuItem))
                .check(matches(withActionIconDrawable(R.drawable.ic_favorite_border_24)));
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.favoriteMenuItem))
                .check(matches(withActionIconDrawable(R.drawable.ic_favorite_border_24)));
        scenario.close();
    }

    @Test
    public void saveFavorite_usingFilter_oneResult() throws Exception {
        String query = "Plane";
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        ActivityScenario<MenuActivity> scenario = ActivityScenario.launch(MenuActivity.class);
        dataBindingIdlingResource.monitorActivity(scenario);

        onView(withId(androidx.appcompat.R.id.search_button)).perform(click());
        onView(withId(androidx.appcompat.R.id.search_src_text))
                .perform(typeText(query), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(withId(R.id.favoritesNav)).perform(click());
        onView(withId(R.id.favoritesRecycler))
                .check(matches(withRecyclerViewItemCount(1)));
        scenario.close();
    }

    @Test
    public void deleteFavorite_usingFilter_zeroResults() throws Exception {
        String query = "Plane";
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        ActivityScenario<MenuActivity> scenario = ActivityScenario.launch(MenuActivity.class);
        dataBindingIdlingResource.monitorActivity(scenario);

        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(withId(R.id.favoritesNav)).perform(click());
        onView(withId(R.id.favoritesRecycler))
                .check(matches(withRecyclerViewItemCount(1)));
        onView(isRoot()).perform(pressBack());
        onView(withId(androidx.appcompat.R.id.search_button)).perform(click());
        onView(withId(androidx.appcompat.R.id.search_src_text))
                .perform(typeText(query), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(withId(R.id.favoritesNav)).perform(click());
        onView(withId(R.id.favoritesRecycler))
                .check(matches(withRecyclerViewItemCount(0)));
        scenario.close();
    }

    @Test
    public void saveFavorite_withExcessCapacity_keepSaveLimit() throws IOException {
        int expectedNumberFavoriteMovies = 2;
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_2.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_3.json");
        ActivityScenario<MenuActivity> scenario = ActivityScenario.launch(MenuActivity.class);
        dataBindingIdlingResource.monitorActivity(scenario);

        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(2, click()));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.ivError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError)).check(matches(withImageDrawable(R.drawable.ic_premium)));
        onView(withText(R.string.ok)).perform(click());
        onView(withId(R.id.favoritesNav)).perform(click());
        onView(withId(R.id.favoritesRecycler))
                .check(matches(withRecyclerViewItemCount(expectedNumberFavoriteMovies)));
        scenario.close();
    }

    @Test
    public void unavailableService_allowShowFavoriteMovies() throws IOException {
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_UNAVAILABLE);
        ActivityScenario<MenuActivity> scenario = ActivityScenario.launch(MenuActivity.class);
        dataBindingIdlingResource.monitorActivity(scenario);

        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.upcomingRecycler)).perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.ivError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError))
                .check(matches(withImageDrawable(R.drawable.ic_service_unavailable)));
        onView(withText(R.string.ok)).perform(click());
        onView(withId(R.id.favoritesNav)).perform(click());
        onView(withId(R.id.favoritesRecycler))
                .check(matches(withRecyclerViewItemCount(1)));
        scenario.close();
    }

}