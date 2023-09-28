package com.vanskarner.cleanmovie.ui.movie;

import com.vanskarner.singleadapter.BindItem;

public class MovieBasicModel implements BindItem {

    public final int id;
    public final String title;
    public String image;

    public MovieBasicModel(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

}