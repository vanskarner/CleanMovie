package com.vanskarner.cleanmovie.main.di;

import android.content.Context;

import com.vanskarner.cleanmovie.main.App;
import com.vanskarner.cleanmovie.main.db.RoomDBModule;
import com.vanskarner.cleanmovie.main.di.modules.ProjectParametersModule;
import com.vanskarner.cleanmovie.main.di.modules.ViewModule;
import com.vanskarner.core.main.CoreModule;
import com.vanskarner.movie.main.MovieModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

/** @noinspection unused*/
@Singleton
@Component(modules = {
        ProjectParametersModule.class,
        RoomDBModule.class,
        CoreModule.class,
        ViewModule.class,
        MovieModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    /** @noinspection unused*/
    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }

}