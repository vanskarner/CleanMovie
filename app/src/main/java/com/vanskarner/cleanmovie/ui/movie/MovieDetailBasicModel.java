package com.vanskarner.cleanmovie.ui.movie;

public class MovieDetailBasicModel {

    public MovieBasicModel basicModel;
    public String backgroundImage;
    public final int voteCount;
    public final float voteAverage;
    public final String releaseDate;
    public final String overview;
    public final boolean recommended;

    public MovieDetailBasicModel(MovieBasicModel basicModel, String backgroundImage,
                                 int voteCount, float voteAverage, String releaseDate, String overview,
                                 boolean recommended) {
        this.basicModel = basicModel;
        this.backgroundImage = backgroundImage;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.recommended = recommended;
    }

    public static MovieDetailBasicModel empty() {
        return new MovieDetailBasicModel(
                MovieBasicModel.empty(),
                "",
                0,
                0,
                "",
                "",
                false);
    }

}