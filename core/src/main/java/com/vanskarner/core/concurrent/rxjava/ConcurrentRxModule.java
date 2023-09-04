package com.vanskarner.core.concurrent.rxjava;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/** @noinspection unused*/
@Module
public abstract class ConcurrentRxModule {

    @Binds
    @Singleton
    abstract RxFutureFactory bindRxFutureFactory(DefaultRxFutureFactory rxFutureFactory);

}