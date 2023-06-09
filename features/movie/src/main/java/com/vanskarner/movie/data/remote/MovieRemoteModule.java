package com.vanskarner.movie.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanskarner.movie.domain.repository.MovieRemoteRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = MovieRemoteErrorsModule.class)
public abstract class MovieRemoteModule {

    private static final int CONNECT_TIME_OUT_SECONDS = 8;
    private static final int READ_TIME_OUT_SECONDS = 8;

    @Binds
    @Singleton
    abstract MovieRemoteRepository bindRepository(MovieRemoteRxRepository repository);

    @Provides
    @Singleton
    static MovieApiClient provideMovieService(
            MovieRemoteErrorInterceptor errorInterceptor,
            MovieDeserializer detailDeserializer,
            @MovieRemoteDataQualifiers.MovieUrl String baseUrl
    ) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(errorInterceptor)
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
            @MovieRemoteDataQualifiers.MovieImageUrl String baseImageUrl
    ) {
        return new MovieDeserializer(baseImageUrl, new Gson());
    }

}