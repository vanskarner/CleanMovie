package com.vanskarner.usecases.movie.ds;

public class MovieDS {

    public int id;
    public String title;
    public String image;

    public MovieDS(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public static MovieDS empty() {
        return new MovieDS(
                0,
                "",
                "");
    }

}