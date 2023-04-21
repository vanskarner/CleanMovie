package com.vanskarner.core;

import javax.inject.Qualifier;

public class CoreQualifiers {
    @Qualifier
    public @interface AsyncCompound {
    }

    @Qualifier
    public @interface ExecutorThread {
    }

    @Qualifier
    public @interface ResponseThread {
    }
}