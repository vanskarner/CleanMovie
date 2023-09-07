package com.vanskarner.movie.businesslogic.services;

import com.vanskarner.movie.businesslogic.bases.BaseUseCase;
import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MoviesFilterDS;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FilterUpcomingMoviesUseCase extends BaseUseCase<MoviesFilterDS, MoviesFilterDS> {

    @Inject
    public FilterUpcomingMoviesUseCase() {
    }

    @Override
    protected MoviesFilterDS buildUseCase(MoviesFilterDS inputValues) {
        String filterPatter = inputValues.query.toString().toLowerCase().trim();
        inputValues.filterList = inputValues.fullList;
        if (filterPatter.isEmpty()) return inputValues;
        List<MovieDS> filteredList = new ArrayList<>();
        for (MovieDS item : inputValues.fullList)
            if (item.title.toLowerCase().contains(filterPatter)) filteredList.add(item);
        inputValues.filterList = filteredList;
        return inputValues;
    }

}