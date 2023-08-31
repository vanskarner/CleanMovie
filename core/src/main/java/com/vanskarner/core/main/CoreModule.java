package com.vanskarner.core.main;

import com.vanskarner.core.concurrent.rxjava.ConcurrentRxModule;
import dagger.Module;

@Module(includes = ConcurrentRxModule.class)
public abstract class CoreModule {
}