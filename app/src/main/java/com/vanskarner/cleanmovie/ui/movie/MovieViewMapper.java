package com.vanskarner.cleanmovie.ui.movie;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesFilterDS;

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

    public static MoviesFilterDS convert(List<MovieModel> movieModels, CharSequence charSequence) {
        List<MovieDS> list = new ArrayList<>();
        for (MovieModel item : movieModels) list.add(convert(item));
        return new MoviesFilterDS(list, charSequence);
    }

    private static MovieModel convert(MovieDS movieDS) {
        return new MovieModel(movieDS.id, movieDS.title, movieDS.image);
    }

    private static MovieDS convert(MovieModel movieModel) {
        return new MovieDS(movieModel.id, movieModel.title, movieModel.image);
    }

}