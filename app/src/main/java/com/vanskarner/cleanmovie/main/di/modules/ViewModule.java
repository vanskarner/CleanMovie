package com.vanskarner.cleanmovie.main.di.modules;

import com.vanskarner.cleanmovie.ui.errors.types.ViewErrorTypesModule;

import dagger.Module;
import dagger.android.AndroidInjectionModule;

@Module(includes = {
        AndroidInjectionModule.class,
        FragmentBuildersModule.class,
        ViewErrorTypesModule.class
})
public abstract class ViewModule {
}