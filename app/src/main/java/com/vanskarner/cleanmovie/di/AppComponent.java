package com.vanskarner.cleanmovie.di;

import android.content.Context;

import com.vanskarner.cleanmovie.App;
import com.vanskarner.cleanmovie.db.AppDBModule;
import com.vanskarner.cleanmovie.di.modules.ProjectParametersModule;
import com.vanskarner.cleanmovie.di.modules.ViewModule;
import com.vanskarner.core.CoreModule;
import com.vanskarner.movie.MovieModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        ProjectParametersModule.class,
        AppDBModule.class,
        CoreModule.class,
        MovieModule.class,
        ViewModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }

}