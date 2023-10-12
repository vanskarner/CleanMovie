package com.vanskarner.domain.movie;

import com.vanskarner.core.sync.Result;
import com.vanskarner.domain.common.UseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FilterUpcomingMoviesUseCase extends UseCase<Result<MoviesFilterDS>, MoviesFilterDS> {

    @Inject
    public FilterUpcomingMoviesUseCase() {
    }

    @Override
    public Result<MoviesFilterDS> execute(MoviesFilterDS inputValues) {
        String query = inputValues.query.toString().toLowerCase().trim();
        inputValues.filterList = inputValues.fullList;
        if (!query.isEmpty()) {
            List<MovieBasicDS> filteredList = new ArrayList<>();
            for (MovieBasicDS item : inputValues.fullList)
                if (item.title.toLowerCase(Locale.ENGLISH).contains(query)) filteredList.add(item);
            inputValues.filterList = filteredList;
        }
        return Result.success(inputValues);
    }

}