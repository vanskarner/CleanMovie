package com.vanskarner.movie.businesslogic.entities;

public class MovieBOBuilder {
    private int id = 0;
    private String title = "";
    private String image = "";
    private String backgroundImage = "";
    private int voteCount = 0;
    private float voteAverage = 0;
    private String releaseDate = "";
    private String overview = "";

    public MovieBOBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public MovieBOBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieBOBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public MovieBOBuilder withBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this;
    }

    public MovieBOBuilder withVoteCount(int voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public MovieBOBuilder withVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }

    public MovieBOBuilder withReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public MovieBOBuilder withOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public MovieBO build() {
        return new MovieBO(id,
                title,
                image,
                backgroundImage,
                voteCount,
                voteAverage,
                releaseDate,
                overview);
    }

    public static MovieBOBuilder getInstance(){
        return new MovieBOBuilder();
    }

}