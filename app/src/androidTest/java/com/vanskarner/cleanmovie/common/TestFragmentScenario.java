package com.vanskarner.cleanmovie.common;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;

import com.vanskarner.cleanmovie.R;

public class TestFragmentScenario {

    public static <T extends Fragment> FragmentScenario<T>
    createWithEmptyBundle(Class<T> fragmentClass,
                          int graphResId,
                          TestNavHostController controller) {
        return FragmentScenario.launchInContainer(
                fragmentClass,
                Bundle.EMPTY,
                R.style.Theme_CleanMovie,
                createFragmentFactory(fragmentClass, graphResId, controller));
    }

    private static <T extends Fragment> FragmentFactory
    createFragmentFactory(Class<T> fragmentClass,
                          int graphResId,
                          TestNavHostController controller) {
        return new FragmentFactory() {
            @NonNull
            @Override
            public Fragment instantiate(@NonNull ClassLoader classLoader,
                                        @NonNull String className) {
                try {
                    T fragment = fragmentClass.newInstance();
                    fragment.getViewLifecycleOwnerLiveData()
                            .observeForever(viewLifecycleOwner -> {
                                if (viewLifecycleOwner != null) {
                                    controller.setGraph(graphResId);
                                    Navigation.setViewNavController(fragment.requireView(),
                                            controller);
                                }
                            });
                    return fragment;
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("Unable to instantiate fragment", e);
                }
            }
        };
    }

}