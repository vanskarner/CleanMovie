package com.vanskarner.movie.persistence.remote;

import static org.junit.Assert.assertEquals;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.vanskarner.core.concurrent.rxjava.DefaultRxFutureFactory;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;
import com.vanskarner.movie.persistence.remote.jsonparser.JsonParserFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.persistence.remote.jsonparser.JsonParserService;
import com.vanskarner.movie.persistence.remote.simulatedserver.SimulatedServer;
import com.vanskarner.movie.persistence.remote.simulatedserver.SimulatedServerFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRemoteRxRepositoryTest {
    static SimulatedServer simulatedServer;
    static JsonParserService jsonService;
    static CompositeDisposable compositeDisposable;
    static String baseImageUrl = "https://image.tmdb.org/t/p/w500";
    static MovieRemoteRxRepository repository;

    @BeforeClass
    public static void setupClass() throws IOException {
        simulatedServer = SimulatedServerFactory.create();
        jsonService = JsonParserFactory.create();
        compositeDisposable = new CompositeDisposable();
        simulatedServer.start(1010);

        repository = createRepository(simulatedServer.url());
    }

    @AfterClass
    public static void tearDownClass() throws IOException {
        simulatedServer.shutdown();
    }

    @After
    public void tearDown() {
        compositeDisposable.clear();
    }

    @Test
    public void getMovies_whenHttpIsOK_returnList() throws Exception {
        String jsonPath = "src/test/resources/upcoming_list.json";
        simulatedServer.enqueueFromJsonPath(jsonPath, HttpURLConnection.HTTP_OK);
        List<MovieBO> actualList = repository.getMovies(1).get();
        MoviesResultDTO expectedList = jsonService.fromPath(jsonPath, MoviesResultDTO.class);

        assertEquals(expectedList.results.size(), actualList.size());
    }

    @Test
    public void getMovie_whenHttpIsOK_returnItem() throws Exception {
        String jsonPath = "src/test/resources/upcoming_item.json";
        simulatedServer.enqueueFromJsonPath(jsonPath, HttpURLConnection.HTTP_OK);
        MovieBO actualItem = repository.getMovie(1).get();
        MovieDTO expectedItem = jsonService.fromPath(jsonPath, MovieDTO.class);
        expectedItem.posterPath = baseImageUrl.concat(expectedItem.posterPath);
        expectedItem.backdropPath = baseImageUrl.concat(expectedItem.backdropPath);


        assertEquals(expectedItem.id, actualItem.getId());
        assertEquals(expectedItem.title, actualItem.getTitle());
        assertEquals(expectedItem.posterPath, actualItem.getImage());
        assertEquals(expectedItem.backdropPath, actualItem.getBackgroundImage());
        assertEquals(expectedItem.voteCount, actualItem.getVoteCount());
        assertEquals(expectedItem.voteAverage, actualItem.getVoteAverage(), 0.01);
    }

    @Test(expected = MovieRemoteError.NoInternet.class)
    public void getMovies_WhenNoResponse_throwException() throws Exception {
        MovieRemoteRxRepository serverlessRepository =
                createRepository("https://127.0.0.1:666/");
        serverlessRepository.getMovies(1).get();
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

    private static MovieRemoteRxRepository createRepository(String baseUrl) {
        Scheduler testScheduler = Schedulers.trampoline();
        RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable,
                testScheduler, testScheduler);
        JsonDeserializer<MovieDTO> deserializer = new MovieDeserializer(baseImageUrl);
        Converter.Factory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder()
                .registerTypeAdapter(MovieDTO.class, deserializer)
                .create());
        RemoteDataErrorFilter errorFilter = new MockRemoteDataErrorFilter();
        Interceptor interceptor = new MovieRemoteErrorInterceptor(errorFilter);
        OkHttpClient httpClient = createHttpClient(interceptor);
        MovieApiClient movieApiClient = createApiClient(baseUrl, gsonConverterFactory, httpClient);

        return new MovieRemoteRxRepository(rxFutureFactory, movieApiClient, "any");
    }

    private static MovieApiClient createApiClient(String baseUrl,
                                                  Converter.Factory factory,
                                                  OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build().create(MovieApiClient.class);
    }

    private static OkHttpClient createHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(80, TimeUnit.MILLISECONDS)
                .readTimeout(80, TimeUnit.MILLISECONDS)
                .writeTimeout(80, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .build();
    }

}