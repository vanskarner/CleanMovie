package com.vanskarner.movie.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

public abstract class MovieRemoteError extends RuntimeException {

    @Singleton
    public static class Unauthorised extends MovieRemoteError {
        @Inject
        public Unauthorised() {
        }
    }

    @Singleton
    public static class NotFound extends MovieRemoteError {
        @Inject
        public NotFound() {
        }
    }

    @Singleton
    public static class Server extends MovieRemoteError {
        @Inject
        public Server() {
        }
    }

    @Singleton
    public static class ServiceUnavailable extends MovieRemoteError {
        @Inject
        public ServiceUnavailable() {
        }
    }

    @Singleton
    public static class NoInternet extends MovieRemoteError {
        @Inject
        public NoInternet() {
        }
    }

}