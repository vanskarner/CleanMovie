package com.vanskarner.movie.domain.services;

import com.vanskarner.movie.domain.bases.BaseUseCase;
import com.vanskarner.movie.domain.ds.MovieDS;
import com.vanskarner.movie.domain.ds.MoviesFilterDS;

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