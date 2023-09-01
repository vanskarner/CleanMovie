package com.vanskarner.cleanmovie.main.di;

import android.content.Context;

import com.vanskarner.cleanmovie.main.TestApp;
import com.vanskarner.cleanmovie.di.modules.ViewModule;
import com.vanskarner.cleanmovie.features.MenuActivityTest;
import com.vanskarner.cleanmovie.features.movie.favorites.FavoritesFragmentTest;
import com.vanskarner.cleanmovie.features.movie.upcoming.UpcomingFragmentTest;
import com.vanskarner.cleanmovie.features.movie.upcomingDetail.UpcomingDetailFragmentTest;
import com.vanskarner.core.main.CoreModule;
import com.vanskarner.localdata.TestLocalDataModule;
import com.vanskarner.remotedata.TestRemoteDataModule;
import com.vanskarner.usecases.DomainModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        TestParametersModule.class,
        CoreModule.class,
        DomainModule.class,
        TestLocalDataModule.class,
        TestRemoteDataModule.class,
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