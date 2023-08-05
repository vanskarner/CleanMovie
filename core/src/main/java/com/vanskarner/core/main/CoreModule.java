package com.vanskarner.core.main;

import com.vanskarner.core.concurrent.rxjava.DefaultRxFutureFactory;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CoreModule {

    @Binds
    @Singleton
    abstract RxFutureFactory bindRxFutureFactory(DefaultRxFutureFactory rxFutureFactory);

}