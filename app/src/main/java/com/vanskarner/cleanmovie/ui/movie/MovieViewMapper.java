package com.vanskarner.cleanmovie.ui.movie;

import com.vanskarner.movie.businesslogic.ds.MovieBasicDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesFilterDS;

import java.util.ArrayList;
import java.util.List;

public final class MovieViewMapper {

    private MovieViewMapper() {
    }

    public static MovieDetailModel convert(MovieDetailDS movieDetailDS) {
        return new MovieDetailModel(
                convert(movieDetailDS.basicInfo),
                movieDetailDS.backgroundImage,
                movieDetailDS.voteCount,
                movieDetailDS.voteAverage,
                movieDetailDS.releaseDate,
                movieDetailDS.overview,
                movieDetailDS.recommended);
    }

    public static MovieDetailDS convert(MovieDetailModel detailModel) {
        return new MovieDetailDS(
                convert(detailModel.basicModel),
                detailModel.backgroundImage,
                detailModel.voteCount,
                detailModel.voteAverage,
                detailModel.releaseDate,
                detailModel.overview,
                detailModel.recommended
        );
    }

    public static List<MovieBasicModel> convert(List<MovieBasicDS> movieDS) {
        List<MovieBasicModel> list = new ArrayList<>();
        for (MovieBasicDS item : movieDS) list.add(convert(item));
        return list;
    }

    public static MoviesFilterDS convert(List<MovieBasicModel> movieBasicModels, CharSequence charSequence) {
        List<MovieBasicDS> list = new ArrayList<>();
        for (MovieBasicModel item : movieBasicModels) list.add(convert(item));
        return new MoviesFilterDS(list, charSequence);
    }

    private static MovieBasicModel convert(MovieBasicDS movieBasicDS) {
        return new MovieBasicModel(movieBasicDS.id, movieBasicDS.title, movieBasicDS.image);
    }

    private static MovieBasicDS convert(MovieBasicModel movieBasicModel) {
        return new MovieBasicDS(movieBasicModel.id, movieBasicModel.title, movieBasicModel.image);
    }

}