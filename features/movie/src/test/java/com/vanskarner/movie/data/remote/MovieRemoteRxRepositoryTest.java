package com.vanskarner.movie.data.remote;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanskarner.core.concurrent.rxjava.DefaultRxFutureFactory;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;
import com.vanskarner.movie.data.remote.utils.TestMockWebServer;
import com.vanskarner.movie.domain.entities.MovieBO;
import com.vanskarner.movie.domain.repository.MovieRemoteRepository;

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
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRemoteRxRepositoryTest {
    MockWebServer server = new MockWebServer();
    MovieRemoteRepository repository;
    String baseImageUrl = "https://image.tmdb.org/t/p/w500";
    Gson gson = new Gson();
    TestMockWebServer testMockWebServer = new TestMockWebServer(server, gson);

    @Before
    public void setUp() throws IOException {
        server.start();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Scheduler testScheduler = Schedulers.trampoline();
        RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable,
                testScheduler, testScheduler);
        String baseUrl = server.url("/").toString();
        MockRemoteDataErrorFilter mockRemoteDataErrorFilter = new MockRemoteDataErrorFilter();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS)
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
        server.shutdown();
    }

    @Test
    public void getMovies_httpOk_returnItems() throws IOException {
        String jsonPath = "src/test/resources/upcoming_list.json";
        MoviesResultDTO expected = testMockWebServer.fromJson(jsonPath, MoviesResultDTO.class);
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, jsonPath);
        List<MovieBO> actual = repository.getMovies(1).get();

        assertEquals(expected.results.size(), actual.size());
    }

    @Test
    public void getMovie_httpOk_returnItem() throws IOException {
        String jsonPath = "src/test/resources/upcoming_item.json";
        MovieDTO expected = testMockWebServer.fromJson(jsonPath, MovieDTO.class);
        expected.posterPath = baseImageUrl.concat(expected.posterPath);
        expected.backdropPath = baseImageUrl.concat(expected.backdropPath);
        testMockWebServer.enqueue(HttpURLConnection.HTTP_OK, jsonPath);
        MovieBO actual = repository.getMovie(1).get();


        assertEquals(expected.id, actual.getId());
        assertEquals(expected.title, actual.getTitle());
        assertEquals(expected.posterPath, actual.getImage());
        assertEquals(expected.backdropPath, actual.getBackgroundImage());
        assertEquals(expected.voteCount, actual.getVoteCount());
        assertEquals(expected.voteAverage, actual.getVoteAverage(), 0.01);
    }

    @Test(expected = MovieRemoteError.NoInternet.class)
    public void getMovies_noResponse_noInternetException() {
        repository.getMovies(1).get();
    }

    @Test(expected = MovieRemoteError.Unauthorised.class)
    public void getMovies_httpUnauthorized_unauthorisedException() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_UNAUTHORIZED);
        repository.getMovies(1).get();
    }

    @Test(expected = MovieRemoteError.NotFound.class)
    public void getMovies_httpNotFound_notFoundException() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_NOT_FOUND);
        repository.getMovies(1).get();
    }

    @Test(expected = MovieRemoteError.ServiceUnavailable.class)
    public void getMovies_httpUnavailable_serviceUnavailableException() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_UNAVAILABLE);
        repository.getMovies(1).get();
    }

    @Test(expected = MovieRemoteError.Server.class)
    public void getMovies_httpOthers_defaultServerException() {
        testMockWebServer.enqueueEmpty(HttpURLConnection.HTTP_VERSION);
        repository.getMovies(1).get();
    }

}