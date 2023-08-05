package com.vanskarner.core.main;

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