package com.vanskarner.movie.businesslogic.ds;

import java.util.Collections;
import java.util.List;

public class MoviesFilterDS {

    public List<MovieBasicDS> fullList;
    public CharSequence query;
    public List<MovieBasicDS> filterList = Collections.emptyList();

    public MoviesFilterDS(List<MovieBasicDS> fullList, CharSequence query, List<MovieBasicDS> filterList) {
        this.fullList = fullList;
        this.query = query;
        this.filterList = filterList;
    }

    public MoviesFilterDS(List<MovieBasicDS> fullList, CharSequence query) {
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