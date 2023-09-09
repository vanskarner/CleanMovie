package com.vanskarner.cleanmovie.ui.movie;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;

import java.util.ArrayList;
import java.util.List;

public class MovieViewMapper {

    private MovieViewMapper() {
    }

    public static MovieDetailModel convert(MovieDetailDS movieDetailDS) {
        return new MovieDetailModel(
                movieDetailDS.id,
                movieDetailDS.title,
                movieDetailDS.image,
                movieDetailDS.backgroundImage,
                movieDetailDS.voteCount,
                movieDetailDS.voteAverage,
                movieDetailDS.releaseDate,
                movieDetailDS.overview,
                movieDetailDS.recommended);
    }

    public static MovieDetailDS convert(MovieDetailModel detailModel) {
        return new MovieDetailDS(
                detailModel.id,
                detailModel.title,
                detailModel.image,
                detailModel.backgroundImage,
                detailModel.voteCount,
                detailModel.voteAverage,
                detailModel.releaseDate,
                detailModel.overview,
                detailModel.recommended
        );
    }

    public static List<MovieModel> convert(List<MovieDS> movieDS) {
        List<MovieModel> list = new ArrayList<>();
        for (MovieDS item : movieDS) list.add(convert(item));
        return list;
    }

    private static MovieModel convert(MovieDS movieDS) {
        return new MovieModel(movieDS.id, movieDS.title, movieDS.image);
    }

}