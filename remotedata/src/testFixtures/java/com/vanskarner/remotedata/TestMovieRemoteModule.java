package com.vanskarner.remotedata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanskarner.usecases.movie.repository.MovieRemoteRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
abstract class TestMovieRemoteModule {
    private static final int CONNECT_TIME_OUT_SECONDS = 3;
    private static final int READ_TIME_OUT_SECONDS = 3;

    @Binds
    @Singleton
    abstract MovieRemoteRepository bindRepository(MovieRemoteRxRepository repository);

    @Provides
    @Singleton
    static MovieApiClient provideMovieService(
            RemoteErrorInterceptor errorInterceptor,
            MovieDeserializer detailDeserializer,
            @MovieRemoteDataQualifiers.BaseUrl String baseUrl
    ) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(errorInterceptor)
                .addInterceptor(logging)
                .build();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MovieDTO.class, detailDeserializer)
                .create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build().create(MovieApiClient.class);
    }

    @Provides
    @Singleton
    static MovieDeserializer provideMovieDetailDeserializer(
            @MovieRemoteDataQualifiers.BaseImageUrl String baseImageUrl
    ) {
        return new MovieDeserializer(baseImageUrl, new Gson());
    }
}