package com.vanskarner.usecases.movie;

public class MovieBO {

    private final int id;
    private final String title;
    private final String image;
    private final String backgroundImage;
    private final int voteCount;
    private final float voteAverage;
    private final String releaseDate;
    private final String overview;

    public MovieBO(int id, String title, String image, String backgroundImage, int voteCount,
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public boolean isRecommended() {
        return voteCount >= 75 && voteAverage >= 7.5;
    }

}