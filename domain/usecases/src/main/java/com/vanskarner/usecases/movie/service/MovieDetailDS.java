package com.vanskarner.usecases.movie.service;

public class MovieDetailDS {

    public MovieBasicDS movieBasic;
    public String backgroundImage;
    public int voteCount;
    public float voteAverage;
    public String releaseDate;
    public String overview;
    public boolean recommended;

    public MovieDetailDS(MovieBasicDS movieBasic, String backgroundImage, int voteCount,
                         float voteAverage, String releaseDate, String overview, boolean recommended) {
        this.movieBasic = movieBasic;
        this.backgroundImage = backgroundImage;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.recommended = recommended;
    }

    public MovieDetailDS(MovieBasicDS movieBasic, String backgroundImage, int voteCount,
                         float voteAverage, String releaseDate, String overview) {
        this.movieBasic = movieBasic;
        this.backgroundImage = backgroundImage;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public static MovieDetailDS empty() {
        return new MovieDetailDS(
                MovieBasicDS.empty(),
                "",
                0,
                0,
                "",
                "",
                false);
    }

}