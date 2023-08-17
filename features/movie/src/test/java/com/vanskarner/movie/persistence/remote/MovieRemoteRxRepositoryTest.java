package com.vanskarner.movie.persistence.remote;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanskarner.core.concurrent.rxjava.DefaultRxFutureFactory;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;
import com.vanskarner.movie.persistence.remote.utils.DefaultJsonParserService;
import com.vanskarner.movie.persistence.remote.utils.DefaultSimulatedServer;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.persistence.remote.utils.JsonParserService;
import com.vanskarner.movie.persistence.remote.utils.SimulatedServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRemoteRxRepositoryTest {
    SimulatedServer simulatedServer = new DefaultSimulatedServer();
    JsonParserService jsonService = new DefaultJsonParserService();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String baseImageUrl = "https://image.tmdb.org/t/p/w500";
    MovieRemoteRxRepository repository;

    @Before
    public void setUp() throws IOException {
        int port = 3016;
        simulatedServer.start(port);
        Scheduler testScheduler = Schedulers.trampoline();
        RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable,
                testScheduler, testScheduler);
        String baseUrl = "http://127.0.0.1:".concat(port + "/");
        MockRemoteDataErrorFilter mockRemoteDataErrorFilter = new MockRemoteDataErrorFilter();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.MILLISECONDS)
                .readTimeout(30, TimeUnit.MILLISECONDS)
                .writeTimeout(30, TimeUnit.MILLISECONDS)
                .addInterceptor(new MovieRemoteErrorInterceptor(mockRemoteDataErrorFilter))
                .build();
        MovieDeserializer detailDeserializer = new MovieDeserializer(baseImageUrl, new Gson());
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MovieDTO.class, detailDeserializer)
                .create();
        MovieApiClient movieApiClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build().create(MovieApiClient.class);
        String apiKey = "any";

        repository = new MovieRemoteRxRepository(rxFutureFactory, movieApiClient, apiKey);
    }

    @After
    public void tearDown() throws Exception {
        compositeDisposable.clear();
        simulatedServer.shutdown();
    }

    @Test
    public void getMovies_whenHttpIsOK_returnList() throws Exception {
        String jsonPath = "src/test/resources/upcoming_list.json";
        MoviesResultDTO expected = jsonService.fromPath(jsonPath, MoviesResultDTO.class);
        simulatedServer.enqueueFromJsonPath(jsonPath, HttpURLConnection.HTTP_OK);
        List<MovieBO> actual = repository.getMovies(1).get();

        assertEquals(expected.results.size(), actual.size());
    }

    @Test
    public void getMovie_whenHttpIsOK_returnItem() throws Exception {
        String jsonPath = "src/test/resources/upcoming_item.json";
        MovieDTO expected = jsonService.fromPath(jsonPath, MovieDTO.class);
        expected.posterPath = baseImageUrl.concat(expected.posterPath);
        expected.backdropPath = baseImageUrl.concat(expected.backdropPath);
        simulatedServer.enqueueFromJsonPath(jsonPath, HttpURLConnection.HTTP_OK);
        MovieBO actual = repository.getMovie(1).get();


        assertEquals(expected.id, actual.getId());
        assertEquals(expected.title, actual.getTitle());
        assertEquals(expected.posterPath, actual.getImage());
        assertEquals(expected.backdropPath, actual.getBackgroundImage());
        assertEquals(expected.voteCount, actual.getVoteCount());
        assertEquals(expected.voteAverage, actual.getVoteAverage(), 0.01);
    }

    @Test(expected = MovieRemoteError.NoInternet.class)
    public void getMovies_WhenNoResponse_throwException() throws Exception {
        repository.getMovies(1).get();
    }

    @Test(expected = MovieRemoteError.Unauthorised.class)
    public void getMovies_whenHttpUnauthorized_throwException() throws Exception {
        simulatedServer.enqueueEmpty(HttpURLConnection.HTTP_UNAUTHORIZED);
        repository.getMovies(1).get();
    }

    @Test(expected = MovieRemoteError.NotFound.class)
    public void getMovies_whenHttpNotFound_throwException() throws Exception {
        simulatedServer.enqueueEmpty(HttpURLConnection.HTTP_NOT_FOUND);
        repository.getMovies(1).get();
    }

    @Test(expected = MovieRemoteError.ServiceUnavailable.class)
    public void getMovies_whenHttpUnavailable_throwException() throws Exception {
        simulatedServer.enqueueEmpty(HttpURLConnection.HTTP_UNAVAILABLE);
        repository.getMovies(1).get();
    }

    @Test(expected = MovieRemoteError.Server.class)
    public void getMovies_whenHttpOtherErrors_throwException() throws Exception {
        simulatedServer.enqueueEmpty(HttpURLConnection.HTTP_VERSION);
        repository.getMovies(1).get();
    }

}