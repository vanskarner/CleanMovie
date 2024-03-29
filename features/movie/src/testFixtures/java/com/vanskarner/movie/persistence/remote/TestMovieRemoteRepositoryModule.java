package com.vanskarner.movie.persistence.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanskarner.movie.businesslogic.MovieRemoteRepository;
import com.vanskarner.movie.main.MovieRemoteDataQualifiers;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/** @noinspection unused*/
@Module
public abstract class TestMovieRemoteRepositoryModule {
    private static final int CONNECT_TIME_OUT_SECONDS = 3;
    private static final int READ_TIME_OUT_SECONDS = 3;

    @Binds
    @Singleton
    abstract MovieRemoteRepository bindRepository(MovieRemoteRxRepository repository);

    @Provides
    @Singleton
    public static MovieApiClient provideMovieService(
            MovieRemoteErrorInterceptor errorInterceptor,
            MovieDeserializer detailDeserializer,
            @MovieRemoteDataQualifiers.MovieUrl String baseUrl
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
    public static MovieDeserializer provideMovieDetailDeserializer(
            @MovieRemoteDataQualifiers.MovieImageUrl String baseImageUrl
    ) {
        return new MovieDeserializer(baseImageUrl);
    }

    @Binds
    public abstract MovieRemoteError bindDefaultRemoteError(MovieRemoteError.Server error);

    @Binds
    @IntoMap
    @StringKey("NoInternet")
    public abstract MovieRemoteError bindNoInternet(MovieRemoteError.NoInternet error);

    @Binds
    @IntoMap
    @StringKey("401")
    public abstract MovieRemoteError bindUnauthorised(MovieRemoteError.Unauthorised error);

    @Binds
    @IntoMap
    @StringKey("404")
    public abstract MovieRemoteError bindNotFound(MovieRemoteError.NotFound error);

    @Binds
    @IntoMap
    @StringKey("503")
    public abstract MovieRemoteError
    bindServiceUnavailable(MovieRemoteError.ServiceUnavailable error);

}