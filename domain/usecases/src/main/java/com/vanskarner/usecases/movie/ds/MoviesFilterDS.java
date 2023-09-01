package com.vanskarner.usecases.movie.ds;

import java.util.Collections;
import java.util.List;

public class MoviesFilterDS {

    public List<MovieDS> fullList;
    public CharSequence query;
    public List<MovieDS> filterList = Collections.emptyList();

    public MoviesFilterDS(List<MovieDS> fullList, CharSequence query, List<MovieDS> filterList) {
        this.fullList = fullList;
        this.query = query;
        this.filterList = filterList;
    }

    public MoviesFilterDS(List<MovieDS> fullList, CharSequence query) {
        this.fullList = fullList;
        this.query = query;
    }

    public static MoviesFilterDS empty() {
        return new MoviesFilterDS(
                Collections.emptyList(),
                "",
                Collections.emptyList());
    }

}