package com.vanskarner.movie.persistence.remote;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
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
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
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
        RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable, testScheduler, testScheduler);
        String baseUrl = "http://127.0.0.1:".concat(port + "/");
        JsonDeserializer<MovieDTO> deserializer = new MovieDeserializer(baseImageUrl, new Gson());
        Converter.Factory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder()
                .registerTypeAdapter(MovieDTO.class, deserializer)
                .create());
        RemoteDataErrorFilter errorFilter = new MockRemoteDataErrorFilter();
        Interceptor interceptor = new MovieRemoteErrorInterceptor(errorFilter);
        OkHttpClient httpClient = createHttpClient(interceptor);
        MovieApiClient movieApiClient = createApiClient(baseUrl, gsonConverterFactory, httpClient);

        repository = new MovieRemoteRxRepository(rxFutureFactory, movieApiClient, "any");
    }

    @After
    public void tearDown() throws Exception {
        compositeDisposable.clear();
        simulatedServer.shutdown();
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

/*    @Test(expected = MovieRemoteError.NoInternet.class)
    public void getMovies_WhenNoResponse_throwException() throws Exception {
        repository.getMovies(1).get();
    }*/

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

    private MovieApiClient createApiClient(String baseUrl,
                                           Converter.Factory factory,
                                           OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build().create(MovieApiClient.class);
    }

    private OkHttpClient createHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.MILLISECONDS)
                .readTimeout(30, TimeUnit.MILLISECONDS)
                .writeTimeout(30, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .build();
    }

}