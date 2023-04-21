package com.vanskarner.usecases.movie.ds;

public class MovieDetailDS {

    public int id;
    public String title;
    public String image;
    public String backgroundImage;
    public int voteCount;
    public float voteAverage;
    public String releaseDate;
    public String overview;
    public boolean recommended;

    public MovieDetailDS(int id, String title, String image, String backgroundImage, int voteCount,
                         float voteAverage, String releaseDate, String overview, boolean recommended) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.backgroundImage = backgroundImage;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.recommended = recommended;
    }

    public MovieDetailDS(int id, String title, String image, String backgroundImage, int voteCount,
                         float voteAverage, String releaseDate, String overview) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.backgroundImage = backgroundImage;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

}