package com.vanskarner.cleanmovie.ui.movie.upcoming;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.vanskarner.cleanmovie.common.TestCustomMatcher.withImageDrawable;
import static com.vanskarner.cleanmovie.common.TestCustomMatcher.withRecyclerViewItemCount;
import static com.vanskarner.cleanmovie.common.TestCustomViewAction.swipeUpSlow;

import android.content.Context;
import android.view.KeyEvent;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.vanskarner.cleanmovie.common.DataBindingIdlingResource;
import com.vanskarner.cleanmovie.main.TestApp;
import com.vanskarner.cleanmovie.common.TestFragmentScenario;
import com.vanskarner.cleanmovie.R;
import com.vanskarner.core.remote.TestSimulatedServer;
import com.vanskarner.core.remote.TestSimulatedServerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class UpcomingFragmentTest {
    Context context;
    TestSimulatedServer testMockWebServer = TestSimulatedServerFactory.create(this.getClass());
    DataBindingIdlingResource dataBindingIdlingResource = new DataBindingIdlingResource();

    @Before
    public void setUp() throws IOException {
        testMockWebServer.start(8080);
        context = ApplicationProvider.getApplicationContext();
        TestApp testApp = (TestApp) InstrumentationRegistry.getInstrumentation()
                .getTargetContext()
                .getApplicationContext();
        testApp.getComponent().inject(this);
        IdlingRegistry.getInstance().register(dataBindingIdlingResource);
    }

    @After
    public void tearDown() throws IOException {
        testMockWebServer.shutdown();
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource);
    }

    @Test
    public void preciseSearch_showOneMatch() throws IOException {
        String query = "Plane";
        int expectedCount = 1;
        testMockWebServer.enqueueFrom("upcoming_list.json", HttpURLConnection.HTTP_OK);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(androidx.appcompat.R.id.search_button)).perform(click());
        onView(withId(androidx.appcompat.R.id.search_src_text))
                .perform(typeText(query), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.upcomingRecycler))
                .check(matches(withRecyclerViewItemCount(expectedCount)));
        scenario.close();
    }

    @Test
    public void impreciseSearch_showSixMatches() throws IOException {
        String query = "The";
        int expectedCount = 6;
        testMockWebServer.enqueueFrom("upcoming_list.json", HttpURLConnection.HTTP_OK);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(androidx.appcompat.R.id.search_button)).perform(click());
        onView(withId(androidx.appcompat.R.id.search_src_text))
                .perform(typeText(query), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.upcomingRecycler))
                .check(matches(withRecyclerViewItemCount(expectedCount)));
        scenario.close();
    }

    @Test
    public void swipeUp_loadMoreItems() throws IOException {
        int expectedCount = 40;
        testMockWebServer.enqueueFrom("upcoming_list.json", HttpURLConnection.HTTP_OK);
        testMockWebServer.enqueueFrom("upcoming_list.json", HttpURLConnection.HTTP_OK);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.upcomingRecycler))
                .perform(scrollToPosition(16), swipeUpSlow(), swipeUpSlow());
        onView(withId(R.id.upcomingRecycler))
                .check(matches(withRecyclerViewItemCount(expectedCount)));
        scenario.close();
    }

    @Test
    public void swipeUp_usingFilter_notLoadMoreItems() throws IOException {
        int expectedCount = 20;
        testMockWebServer.enqueueFrom("upcoming_list.json", HttpURLConnection.HTTP_OK);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(androidx.appcompat.R.id.search_button)).perform(click());
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.upcomingRecycler))
                .perform(scrollToPosition(16), swipeUpSlow(), swipeUpSlow());
        onView(withId(R.id.upcomingRecycler))
                .check(matches(withRecyclerViewItemCount(expectedCount)));
        scenario.close();
    }

    @Test
    public void httpInternalServerError_showServerError() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_INTERNAL_ERROR);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.tvMsgError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError)).check(matches(withImageDrawable(R.drawable.ic_server_error)));
        onView(withId(R.id.tvMsgError)).check(matches(withText(R.string.msg_server_error)));
        scenario.close();
    }

    @Test
    public void httpUnavailable_showServiceUnavailableError() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_UNAVAILABLE);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.tvMsgError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError)).check(matches(withImageDrawable(R.drawable.ic_service_unavailable)));
        onView(withId(R.id.tvMsgError)).check(matches(withText(R.string.msg_service_unavailable)));
        scenario.close();
    }

    @Test
    public void httpUnauthorized_showUnauthorisedError() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_UNAUTHORIZED);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = TestFragmentScenario
                .createWithEmptyBundle(
                        UpcomingFragment.class,
                        R.navigation.upcoming_nav,
                        controller);
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.tvMsgError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError)).check(matches(withImageDrawable(R.drawable.ic_unauthorised)));
        onView(withId(R.id.tvMsgError)).check(matches(withText(R.string.msg_unauthorized)));
        scenario.close();
    }

}