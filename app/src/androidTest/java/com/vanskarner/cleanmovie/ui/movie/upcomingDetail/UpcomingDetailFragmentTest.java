package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static com.vanskarner.cleanmovie.utils.CustomMatcher.withActionIconDrawable;
import static com.vanskarner.cleanmovie.utils.CustomMatcher.withImageDrawable;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.vanskarner.cleanmovie.utils.DataBindingIdlingResource;
import com.vanskarner.cleanmovie.main.TestApp;
import com.vanskarner.cleanmovie.utils.TestFragmentScenario;
import com.vanskarner.cleanmovie.utils.TestMockWebServer;
import com.vanskarner.cleanmovie.R;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.inject.Inject;

import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class UpcomingDetailFragmentTest {
    Context context;
    MockWebServer server = new MockWebServer();
    TestMockWebServer testMockWebServer = new TestMockWebServer(server);
    DataBindingIdlingResource dataBindingIdlingResource = new DataBindingIdlingResource();
    @Inject
    MovieServices movieServices;

    @Before
    public void setUp() throws IOException {
        server.start(8080);
        context = ApplicationProvider.getApplicationContext();
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
    public void saveFavorite_showFavoriteIcon() throws IOException {
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");
        Bundle bundle = new Bundle();
        bundle.putInt("movieId", 646389);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingDetailFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingDetailFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.favoriteMenuItem))
                .check(matches(withActionIconDrawable(R.drawable.ic_favorite_border_24)));
        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(withId(R.id.favoriteMenuItem))
                .check(matches(withActionIconDrawable(R.drawable.ic_favorite_24)));
        scenario.close();
    }

    @Test
    public void saveFavorite_favoritesLimitError_showErrorDialog() throws Exception {
        MovieDetailDS itemOne = new MovieDetailDS(1, "Clean Architecture",
                "", "", 100, 8.5f,
                "2023-03-01", "Separation of responsibilities");
        MovieDetailDS itemTwo = new MovieDetailDS(2, "Clean Architecture",
                "", "", 100, 8.5f,
                "2023-03-01", "Apply SOLID");
        movieServices.toggleFavorite(itemOne).get();
        movieServices.toggleFavorite(itemTwo).get();
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_item_1.json");

        Bundle bundle = new Bundle();
        bundle.putInt("movieId", 646389);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingDetailFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingDetailFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.favoriteMenuItem)).perform(click());
        onView(withId(R.id.ivError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError)).check(matches(withImageDrawable(R.drawable.ic_premium)));
        scenario.close();
    }

    @Test
    public void notFoundError_showErrorDialog() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_NOT_FOUND);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingDetailFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingDetailFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.ivError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError)).check(matches(withImageDrawable(R.drawable.ic_not_found)));
        scenario.close();
    }

}