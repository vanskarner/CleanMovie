package com.vanskarner.movie.persistence.remote;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class MovieDTO implements Serializable {

    @SerializedName("id")
    int id;
    @SerializedName("overview")
    String overview;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("backdrop_path")
    String backdropPath;
    @SerializedName("release_date")
    String releaseDate;
    @SerializedName("title")
    String title;
    @SerializedName("vote_count")
    int voteCount;
    @SerializedName("vote_average")
    float voteAverage;

    public MovieDTO(int id, String overview, String posterPath, String backdropPath,
                    String releaseDate, String title, int voteCount, float voteAverage) {
        this.id = id;
        this.overview = overview;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
    }

}