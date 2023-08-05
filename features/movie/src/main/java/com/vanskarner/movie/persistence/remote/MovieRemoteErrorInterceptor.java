package com.vanskarner.movie.persistence.remote;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
class MovieRemoteErrorInterceptor implements Interceptor {

    private final RemoteDataErrorFilter errorFilter;

    @Inject
    public MovieRemoteErrorInterceptor(RemoteDataErrorFilter errorFilter) {
        this.errorFilter = errorFilter;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) {
        Request request = chain.request();
        try {
            Response response = chain.proceed(request);
            if (response.isSuccessful()) return response;
            throw errorFilter.filter(Integer.toString(response.code()));
        } catch (IOException ignored) {
            throw errorFilter.getNoInternetError();
        }
    }

}