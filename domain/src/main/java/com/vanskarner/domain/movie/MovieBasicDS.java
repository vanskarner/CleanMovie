package com.vanskarner.domain.movie;

public class MovieBasicDS {

    public int id;
    public String title;
    public String image;

    public MovieBasicDS(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public static MovieBasicDS empty() {
        return new MovieBasicDS(
                0,
                "",
                "");
    }

}