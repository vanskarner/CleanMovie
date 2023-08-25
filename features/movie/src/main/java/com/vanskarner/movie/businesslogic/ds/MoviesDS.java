package com.vanskarner.movie.businesslogic.ds;

import java.util.Collections;
import java.util.List;

public class MoviesDS {

    public List<MovieDS> list;

    public MoviesDS(List<MovieDS> list) {
        this.list = list;
    }

    public static MoviesDS empty() {
        return new MoviesDS(Collections.emptyList());
    }

}