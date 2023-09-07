package com.vanskarner.movie.presentation;

public class MovieModel {

    public final int id;
    public final String title;
    public String image;

    public MovieModel(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

}