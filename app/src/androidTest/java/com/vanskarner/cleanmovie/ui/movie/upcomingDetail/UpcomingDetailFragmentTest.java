package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static com.vanskarner.cleanmovie.common.TestCustomMatcher.withActionIconDrawable;
import static com.vanskarner.cleanmovie.common.TestCustomMatcher.withImageDrawable;

import android.content.Context;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.vanskarner.cleanmovie.common.DataBindingIdlingResource;
import com.vanskarner.cleanmovie.main.TestApp;
import com.vanskarner.cleanmovie.common.MovieDetailDSMother;
import com.vanskarner.cleanmovie.common.TestFragmentScenario;
import com.vanskarner.cleanmovie.R;
import com.vanskarner.core.remote.TestSimulatedServer;
import com.vanskarner.core.remote.TestSimulatedServerFactory;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.services.MovieServices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class UpcomingDetailFragmentTest {
    Context context;
    TestSimulatedServer simulatedServer = TestSimulatedServerFactory.create(this.getClass());
    DataBindingIdlingResource dataBindingIdlingResource = new DataBindingIdlingResource();
    @Inject
    MovieServices movieServices;

    @Before
    public void setUp() throws IOException {
        simulatedServer.start(8080);
        context = ApplicationProvider.getApplicationContext();
        TestApp testApp = (TestApp) InstrumentationRegistry.getInstrumentation()
                .getTargetContext()
                .getApplicationContext();
        testApp.getComponent().inject(this);
        IdlingRegistry.getInstance().register(dataBindingIdlingResource);
    }

    @After
    public void tearDown() throws Exception {
        simulatedServer.shutdown();
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource);
        movieServices.deleteAllFavorite().get();
    }

    @Test
    public void saveFavorite_showMarkedAsFavorite() throws IOException {
        simulatedServer.enqueueFrom("upcoming_item_1.json", HttpURLConnection.HTTP_OK);
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
    public void saveFavorite_withExcessCapacity_showFavoritesLimitError() throws Exception {
        MovieDetailDS itemOne = MovieDetailDSMother.createWith(1);
        MovieDetailDS itemTwo = MovieDetailDSMother.createWith(2);
        movieServices.toggleFavorite(itemOne).get();
        movieServices.toggleFavorite(itemTwo).get();
        simulatedServer.enqueueFrom("upcoming_item_1.json", HttpURLConnection.HTTP_OK);
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
    public void httpNotFound_showNotFoundError() {
        simulatedServer.enqueueEmpty(HttpURLConnection.HTTP_NOT_FOUND);
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