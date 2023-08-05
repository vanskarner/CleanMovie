package com.vanskarner.cleanmovie.di.modules;

import com.vanskarner.cleanmovie.ui.errors.ViewErrorTypesModule;

import dagger.Module;
import dagger.android.AndroidInjectionModule;

@Module(includes = {
        AndroidInjectionModule.class,
        FragmentBuildersModule.class,
        ViewErrorTypesModule.class
})
public abstract class ViewModule {
}