package com.vanskarner.cleanmovie;

import com.vanskarner.cleanmovie.main.App;
import com.vanskarner.cleanmovie.main.di.DaggerTestAppComponent;
import com.vanskarner.cleanmovie.main.di.TestAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class TestApp extends App {

    private TestAppComponent component;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        component = DaggerTestAppComponent.factory().create(this);
        return component;
    }

    public TestAppComponent getComponent() {
        return component;
    }
}