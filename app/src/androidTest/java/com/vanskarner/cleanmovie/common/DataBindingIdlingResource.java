package com.vanskarner.cleanmovie.common;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class DataBindingIdlingResource implements IdlingResource {
    private static final String id = UUID.randomUUID().toString();
    private static final List<ResourceCallback> idlingCallbacks = new ArrayList<>();
    private boolean wasNotIdle;
    private FragmentActivity activity;

    private DataBindingIdlingResource() {
    }

    @Override
    public String getName() {
        return "DataBinding ".concat(id);
    }

    @Override
    public boolean isIdleNow() {
        boolean idle = false;
        for (ViewDataBinding b : getBindings()) {
            if (b != null && !b.hasPendingBindings()) {
                idle = true;
                break;
            }
        }
        if (idle) {
            if (wasNotIdle)
                // Notify observers to avoid Espresso race detector.
                for (ResourceCallback cb : idlingCallbacks) cb.onTransitionToIdle();
            wasNotIdle = false;
        } else {
            wasNotIdle = true;
            // Check next frame.
            if (activity != null)
                activity.findViewById(android.R.id.content)
                        .postDelayed(this::isIdleNow, 16);
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        idlingCallbacks.add(callback);
    }

    public <T extends FragmentActivity> void monitorActivity(ActivityScenario<T> activityScenario) {
        activityScenario.onActivity(this::monitorActivity);
    }

    public <T extends Fragment> void monitorFragment(FragmentScenario<T> fragmentScenario) {
        fragmentScenario.onFragment(this::monitorFragment);
    }

    public <T extends FragmentActivity> void monitorActivity(T activity) {
        this.activity = activity;
    }

    public <T extends Fragment> void monitorFragment(T fragment) {
        activity = fragment.requireActivity();
    }

    @Nullable
    private ViewDataBinding getBinding(View view) {
        return DataBindingUtil.getBinding(view);
    }

    private List<ViewDataBinding> getBindings() {
        List<Fragment> fragments = activity == null ?
                Collections.emptyList() : activity.getSupportFragmentManager().getFragments();

        List<ViewDataBinding> bindings = new ArrayList<>();
        for (Fragment f : fragments) {
            if (f.getView() == null) continue;
            bindings.add(getBinding(f.getView()));
            for (Fragment cf : f.getChildFragmentManager().getFragments()) {
                if (cf.getView() == null) continue;
                bindings.add(getBinding(cf.getView()));
            }
        }
        return bindings;
    }

    public static DataBindingIdlingResource newInstance() {
        return new DataBindingIdlingResource();
    }

}