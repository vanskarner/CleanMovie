package com.vanskarner.movie.businesslogic.ds;

import java.util.Collections;
import java.util.List;

public class MoviesDS {

    public List<MovieBasicDS> list;

    public MoviesDS(List<MovieBasicDS> list) {
        this.list = list;
    }

    public static MoviesDS empty() {
        return new MoviesDS(Collections.emptyList());
    }

}