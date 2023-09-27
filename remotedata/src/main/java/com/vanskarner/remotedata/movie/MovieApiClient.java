package com.vanskarner.remotedata.movie;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface MovieApiClient {

    String baseEndPoint = "movie";
    String language = "en";

    @GET(baseEndPoint + "/upcoming?" + "language=" + language)
    Single<MoviesResultDTO> getUpcomingMovies(@Query("page") int page,
                                              @Query("api_key") String apiKey);

    @GET(baseEndPoint + "/{movie_id}?" + "language=" + language)
    Single<MovieDTO> getMovieDetail(@Path("movie_id") int movieId,
                                    @Query("api_key") String apiKey);

}