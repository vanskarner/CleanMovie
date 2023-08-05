package com.vanskarner.cleanmovie.di;

import com.vanskarner.cleanmovie.BuildConfig;
import com.vanskarner.cleanmovie.di.qualiers.ViewQualifiers;
import com.vanskarner.core.CoreQualifiers;
import com.vanskarner.movie.persistence.remote.MovieRemoteDataQualifiers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Module
public class TestParametersModule {

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
        return Schedulers.trampoline();
    }

    @Provides
    @Singleton
    @CoreQualifiers.AsyncCompound
    static CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    @MovieRemoteDataQualifiers.MovieUrl
    static String provideBaseurl() {
        return "http://localhost:8080/";
    }

    @Provides
    @Singleton
    @MovieRemoteDataQualifiers.MovieImageUrl
    static String provideBaseImageUrl() {
        return BuildConfig.themoviedbImageURL;
    }

    @Provides
    @Singleton
    @MovieRemoteDataQualifiers.Apikey
    static String provideApiKey() {
        return "any";
    }

}