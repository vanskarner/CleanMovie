package com.vanskarner.movie.businesslogic;

public class MovieDetailDS {
    public MovieBasicDS basicInfo;
    public String backgroundImage;
    public int voteCount;
    public float voteAverage;
    public String releaseDate;
    public String overview;
    public boolean recommended;

    public MovieDetailDS(MovieBasicDS basicInfo, String backgroundImage, int voteCount,
                         float voteAverage, String releaseDate, String overview, boolean recommended) {
        this.basicInfo = basicInfo;
        this.backgroundImage = backgroundImage;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.recommended = recommended;
    }

    public MovieDetailDS(MovieBasicDS basicInfo, String backgroundImage, int voteCount,
                         float voteAverage, String releaseDate, String overview) {
        this.basicInfo = basicInfo;
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