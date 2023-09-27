package com.vanskarner.cleanmovie.main.di;

import android.content.Context;

import com.vanskarner.cleanmovie.main.App;
import com.vanskarner.cleanmovie.main.db.RoomDBModule;
import com.vanskarner.cleanmovie.main.di.modules.ProjectParametersModule;
import com.vanskarner.cleanmovie.main.di.modules.ViewModule;
import com.vanskarner.core.main.CoreModule;
import com.vanskarner.localdata.main.LocalDataModule;
import com.vanskarner.remotedata.main.RemoteDataModule;
import com.vanskarner.usecases.main.DomainModule;

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
        DomainModule.class,
        LocalDataModule.class,
        RemoteDataModule.class,
        ViewModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }

}