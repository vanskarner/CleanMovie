package com.vanskarner.cleanmovie.main.di.modules;

import com.vanskarner.cleanmovie.BuildConfig;
import com.vanskarner.cleanmovie.main.di.qualiers.ViewQualifiers;
import com.vanskarner.core.main.CoreQualifiers;
import com.vanskarner.remotedata.MovieRemoteDataQualifiers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Module
public abstract class ProjectParametersModule {

    @Provides
    @Singleton
    @CoreQualifiers.ExecutorThread
    static Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @CoreQualifiers.ResponseThread
    static Scheduler provideUIThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @ViewQualifiers.ComputingThread
    static Scheduler provideSchedulerComputation() {
        return Schedulers.computation();
    }

    @Provides
    @Singleton
    @CoreQualifiers.AsyncCompound
    static CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    @MovieRemoteDataQualifiers.BaseUrl
    static String provideBaseurl() {
        return BuildConfig.themoviedbURL;
    }

    @Provides
    @Singleton
    @MovieRemoteDataQualifiers.BaseImageUrl
    static String provideBaseImageUrl() {
        return BuildConfig.themoviedbImageURL;
    }

    @Provides
    @Singleton
    @MovieRemoteDataQualifiers.Apikey
    static String provideApiKey() {
        return BuildConfig.themoviedbApiKey;
    }

}