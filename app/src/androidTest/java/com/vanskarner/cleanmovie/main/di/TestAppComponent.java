package com.vanskarner.cleanmovie.main.di;

import android.content.Context;

import com.vanskarner.cleanmovie.TestApp;
import com.vanskarner.cleanmovie.main.db.TestAppDBModule;
import com.vanskarner.cleanmovie.main.di.modules.ViewModule;
import com.vanskarner.cleanmovie.ui.MenuActivityTest;
import com.vanskarner.cleanmovie.ui.movie.favorites.FavoritesFragmentTest;
import com.vanskarner.cleanmovie.ui.movie.upcoming.UpcomingFragmentTest;
import com.vanskarner.cleanmovie.ui.movie.upcomingDetail.UpcomingDetailFragmentTest;
import com.vanskarner.core.CoreModule;
import com.vanskarner.movie.MovieTestModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        TestParametersModule.class,
        TestAppDBModule.class,
        CoreModule.class,
        MovieTestModule.class,
        ViewModule.class
})
public interface TestAppComponent extends AndroidInjector<TestApp> {

    @Component.Factory
    interface Factory {
        TestAppComponent create(@BindsInstance Context context);
    }

    void inject(MenuActivityTest test);

    void inject(FavoritesFragmentTest test);

    void inject(UpcomingDetailFragmentTest test);

    void inject(UpcomingFragmentTest test);
}