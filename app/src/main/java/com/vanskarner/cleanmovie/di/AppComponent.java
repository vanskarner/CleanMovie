package com.vanskarner.cleanmovie.di;

import android.content.Context;

import com.vanskarner.cleanmovie.App;
import com.vanskarner.cleanmovie.di.modules.ProjectParametersModule;
import com.vanskarner.cleanmovie.di.modules.ViewModule;
import com.vanskarner.core.CoreModule;
import com.vanskarner.localdata.LocalDataModule;
import com.vanskarner.remotedata.RemoteDataModule;
import com.vanskarner.usecases.DomainModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        ProjectParametersModule.class,
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