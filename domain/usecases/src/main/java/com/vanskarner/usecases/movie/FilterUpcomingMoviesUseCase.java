package com.vanskarner.usecases.movie;

import com.vanskarner.usecases.bases.BaseUseCase;
import com.vanskarner.usecases.movie.ds.MovieBasicDS;
import com.vanskarner.usecases.movie.ds.MoviesFilterDS;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FilterUpcomingMoviesUseCase extends BaseUseCase<MoviesFilterDS, MoviesFilterDS> {

    @Inject
    public FilterUpcomingMoviesUseCase() {
    }

    @Override
    protected MoviesFilterDS buildUseCase(MoviesFilterDS inputValues) {
        String query = inputValues.query.toString().toLowerCase().trim();
        inputValues.filterList = inputValues.fullList;
        if (!query.isEmpty()) {
            List<MovieBasicDS> filteredList = new ArrayList<>();
            for (MovieBasicDS item : inputValues.fullList)
                if (item.title.toLowerCase(Locale.ENGLISH).contains(query)) filteredList.add(item);
            inputValues.filterList = filteredList;
        }
        return inputValues;
    }

}