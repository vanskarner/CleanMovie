package com.vanskarner.cleanmovie.features.movie.upcoming;

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

import static com.vanskarner.cleanmovie.utils.CustomMatcher.withImageDrawable;
import static com.vanskarner.cleanmovie.utils.CustomMatcher.withRecyclerViewItemCount;
import static com.vanskarner.cleanmovie.utils.CustomViewAction.swipeUpSlow;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.vanskarner.cleanmovie.DataBindingIdlingResource;
import com.vanskarner.cleanmovie.TestApp;
import com.vanskarner.cleanmovie.utils.TestMockWebServer;
import com.vanskarner.cleanmovie.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class UpcomingFragmentTest {
    Context context;
    MockWebServer server = new MockWebServer();
    TestMockWebServer testMockWebServer = new TestMockWebServer(server);
    DataBindingIdlingResource dataBindingIdlingResource = new DataBindingIdlingResource();

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
    public void tearDown() throws IOException {
        server.shutdown();
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource);
    }

    @Test
    public void preciseSearch_showOneMatch() throws IOException {
        String query = "Plane";
        int expectedCount = 1;
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = FragmentScenario.launchInContainer(
                UpcomingFragment.class, Bundle.EMPTY, R.style.Theme_CleanMovie,
                new FragmentFactory() {
                    @NonNull
                    @Override
                    public Fragment instantiate(@NonNull ClassLoader classLoader,
                                                @NonNull String className) {
                        UpcomingFragment fragment = new UpcomingFragment();
                        fragment.getViewLifecycleOwnerLiveData()
                                .observeForever(viewLifecycleOwner -> {
                                    if (viewLifecycleOwner != null) {
                                        controller.setGraph(R.navigation.upcoming_nav);
                                        View view = fragment.requireView();
                                        Navigation.setViewNavController(view, controller);
                                    }
                                });
                        return fragment;
                    }
                });
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
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = FragmentScenario.launchInContainer(
                UpcomingFragment.class, Bundle.EMPTY, R.style.Theme_CleanMovie,
                new FragmentFactory() {
                    @NonNull
                    @Override
                    public Fragment instantiate(@NonNull ClassLoader classLoader,
                                                @NonNull String className) {
                        UpcomingFragment fragment = new UpcomingFragment();
                        fragment.getViewLifecycleOwnerLiveData()
                                .observeForever(viewLifecycleOwner -> {
                                    if (viewLifecycleOwner != null) {
                                        controller.setGraph(R.navigation.upcoming_nav);
                                        View view = fragment.requireView();
                                        Navigation.setViewNavController(view, controller);
                                    }
                                });
                        return fragment;
                    }
                });
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(androidx.appcompat.R.id.search_button)).perform(click());
        onView(withId(androidx.appcompat.R.id.search_src_text))
                .perform(typeText(query), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.upcomingRecycler))
                .check(matches(withRecyclerViewItemCount(expectedCount)));
        scenario.close();
    }

    @Test
    public void swipeAction_loadMoreItems() throws IOException {
        int expectedCount = 40;
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = FragmentScenario.launchInContainer(
                UpcomingFragment.class, Bundle.EMPTY, R.style.Theme_CleanMovie,
                new FragmentFactory() {
                    @NonNull
                    @Override
                    public Fragment instantiate(@NonNull ClassLoader classLoader,
                                                @NonNull String className) {
                        UpcomingFragment fragment = new UpcomingFragment();
                        fragment.getViewLifecycleOwnerLiveData()
                                .observeForever(viewLifecycleOwner -> {
                                    if (viewLifecycleOwner != null) {
                                        controller.setGraph(R.navigation.upcoming_nav);
                                        View view = fragment.requireView();
                                        Navigation.setViewNavController(view, controller);
                                    }
                                });
                        return fragment;
                    }
                });
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.upcomingRecycler))
                .perform(scrollToPosition(16), swipeUpSlow(), swipeUpSlow());
        onView(withId(R.id.upcomingRecycler))
                .check(matches(withRecyclerViewItemCount(expectedCount)));
        scenario.close();
    }

    @Test
    public void swipeAction_usingFilter_notLoadMoreItems() throws IOException {
        int expectedCount = 20;
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, "upcoming_list.json");
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = FragmentScenario.launchInContainer(
                UpcomingFragment.class, Bundle.EMPTY, R.style.Theme_CleanMovie,
                new FragmentFactory() {
                    @NonNull
                    @Override
                    public Fragment instantiate(@NonNull ClassLoader classLoader,
                                                @NonNull String className) {
                        UpcomingFragment fragment = new UpcomingFragment();
                        fragment.getViewLifecycleOwnerLiveData()
                                .observeForever(viewLifecycleOwner -> {
                                    if (viewLifecycleOwner != null) {
                                        controller.setGraph(R.navigation.upcoming_nav);
                                        View view = fragment.requireView();
                                        Navigation.setViewNavController(view, controller);
                                    }
                                });
                        return fragment;
                    }
                });
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
    public void severError_showErrorDialog() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_INTERNAL_ERROR);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = FragmentScenario.launchInContainer(
                UpcomingFragment.class, Bundle.EMPTY, R.style.Theme_CleanMovie,
                new FragmentFactory() {
                    @NonNull
                    @Override
                    public Fragment instantiate(@NonNull ClassLoader classLoader,
                                                @NonNull String className) {
                        UpcomingFragment fragment = new UpcomingFragment();
                        fragment.getViewLifecycleOwnerLiveData()
                                .observeForever(viewLifecycleOwner -> {
                                    if (viewLifecycleOwner != null) {
                                        controller.setGraph(R.navigation.upcoming_nav);
                                        View view = fragment.requireView();
                                        Navigation.setViewNavController(view, controller);
                                    }
                                });
                        return fragment;
                    }
                });
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.tvMsgError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError)).check(matches(withImageDrawable(R.drawable.ic_server_error)));
        onView(withId(R.id.tvMsgError)).check(matches(withText(R.string.msg_server_error)));
        scenario.close();
    }

    @Test
    public void unavailableError_showErrorDialog() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_UNAVAILABLE);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = FragmentScenario.launchInContainer(
                UpcomingFragment.class, Bundle.EMPTY, R.style.Theme_CleanMovie,
                new FragmentFactory() {
                    @NonNull
                    @Override
                    public Fragment instantiate(@NonNull ClassLoader classLoader,
                                                @NonNull String className) {
                        UpcomingFragment fragment = new UpcomingFragment();
                        fragment.getViewLifecycleOwnerLiveData()
                                .observeForever(viewLifecycleOwner -> {
                                    if (viewLifecycleOwner != null) {
                                        controller.setGraph(R.navigation.upcoming_nav);
                                        View view = fragment.requireView();
                                        Navigation.setViewNavController(view, controller);
                                    }
                                });
                        return fragment;
                    }
                });
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.tvMsgError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError)).check(matches(withImageDrawable(R.drawable.ic_service_unavailable)));
        onView(withId(R.id.tvMsgError)).check(matches(withText(R.string.msg_service_unavailable)));
        scenario.close();
    }

    @Test
    public void unauthorisedError_showErrorDialog() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_UNAUTHORIZED);
        TestNavHostController controller = new TestNavHostController(context);
        FragmentScenario<UpcomingFragment> scenario = FragmentScenario.launchInContainer(
                UpcomingFragment.class, Bundle.EMPTY, R.style.Theme_CleanMovie,
                new FragmentFactory() {
                    @NonNull
                    @Override
                    public Fragment instantiate(@NonNull ClassLoader classLoader,
                                                @NonNull String className) {
                        UpcomingFragment fragment = new UpcomingFragment();
                        fragment.getViewLifecycleOwnerLiveData()
                                .observeForever(viewLifecycleOwner -> {
                                    if (viewLifecycleOwner != null) {
                                        controller.setGraph(R.navigation.upcoming_nav);
                                        View view = fragment.requireView();
                                        Navigation.setViewNavController(view, controller);
                                    }
                                });
                        return fragment;
                    }
                });
        dataBindingIdlingResource.monitorFragment(scenario);

        onView(withId(R.id.tvMsgError)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.ivError)).check(matches(withImageDrawable(R.drawable.ic_unauthorised)));
        onView(withId(R.id.tvMsgError)).check(matches(withText(R.string.msg_unauthorized)));
        scenario.close();
    }

}