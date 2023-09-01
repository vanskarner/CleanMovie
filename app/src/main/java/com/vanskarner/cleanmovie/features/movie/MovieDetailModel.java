package com.vanskarner.cleanmovie.features.movie;

public class MovieDetailModel extends MovieModel {

    public String backgroundImage;
    public final int voteCount;
    public final float voteAverage;
    public final String releaseDate;
    public final String overview;
    public final boolean recommended;

    public MovieDetailModel(int id, String title, String image, String backgroundImage,
                            int voteCount, float voteAverage, String releaseDate, String overview,
                            boolean recommended) {
        super(id, title, image);
        this.backgroundImage = backgroundImage;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.recommended = recommended;
    }

    public static MovieDetailModel empty() {
        return new MovieDetailModel(
                0,
                "",
                "",
                "",
                0,
                0,
                "",
                "",
                false);
    }

}