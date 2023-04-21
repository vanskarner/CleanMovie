package com.vanskarner.remotedata;

import javax.inject.Inject;
import javax.inject.Singleton;

public abstract class RemoteError extends RuntimeException {

    @Singleton
    public static class Unauthorised extends RemoteError {
        @Inject
        public Unauthorised() {
        }
    }

    @Singleton
    public static class NotFound extends RemoteError {
        @Inject
        public NotFound() {
        }
    }

    @Singleton
    public static class Server extends RemoteError {
        @Inject
        public Server() {
        }
    }

    @Singleton
    public static class ServiceUnavailable extends RemoteError {
        @Inject
        public ServiceUnavailable() {
        }
    }

    @Singleton
    public static class NoInternet extends RemoteError {
        @Inject
        public NoInternet() {
        }
    }

}