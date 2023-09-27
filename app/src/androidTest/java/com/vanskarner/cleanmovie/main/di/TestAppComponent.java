package com.vanskarner.cleanmovie.main.di;

import android.content.Context;

import com.vanskarner.cleanmovie.main.TestApp;
import com.vanskarner.cleanmovie.main.db.TestAppDBModule;
import com.vanskarner.cleanmovie.main.di.modules.ViewModule;
import com.vanskarner.cleanmovie.ui.MenuActivityTest;
import com.vanskarner.cleanmovie.ui.movie.favorites.FavoritesFragmentTest;
import com.vanskarner.cleanmovie.ui.movie.upcoming.UpcomingFragmentTest;
import com.vanskarner.cleanmovie.ui.movie.upcomingDetail.UpcomingDetailFragmentTest;
import com.vanskarner.core.main.CoreModule;
import com.vanskarner.localdata.main.LocalDataModule;
import com.vanskarner.remotedata.main.TestRemoteDataModule;
import com.vanskarner.usecases.main.DomainModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        TestParametersModule.class,
        TestAppDBModule.class,
        CoreModule.class,
        DomainModule.class,
        LocalDataModule.class,
        TestRemoteDataModule.class,
        ViewModule.class
})
public interface TestAppComponent extends AndroidInjector<TestApp> {

    /** @noinspection unused*/
    @Component.Factory
    interface Factory {
        TestAppComponent create(@BindsInstance Context context);
    }

    void inject(MenuActivityTest test);

    void inject(FavoritesFragmentTest test);

    void inject(UpcomingDetailFragmentTest test);

    void inject(UpcomingFragmentTest test);
}