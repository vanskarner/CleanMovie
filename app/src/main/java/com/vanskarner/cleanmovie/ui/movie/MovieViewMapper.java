package com.vanskarner.cleanmovie.ui.movie;

import com.vanskarner.usecases.movie.ds.MovieBasicDS;
import com.vanskarner.usecases.movie.ds.MovieDetailDS;
import com.vanskarner.usecases.movie.ds.MoviesFilterDS;

import java.util.ArrayList;
import java.util.List;

public final class MovieViewMapper {

    private MovieViewMapper() {
    }

    public static MovieDetailModel convert(MovieDetailDS movieDetailDS) {
        return new MovieDetailModel(
                new MovieBasicModel(movieDetailDS.movieBasic.id,
                        movieDetailDS.movieBasic.title,
                        movieDetailDS.movieBasic.image),
                movieDetailDS.backgroundImage,
                movieDetailDS.voteCount,
                movieDetailDS.voteAverage,
                movieDetailDS.releaseDate,
                movieDetailDS.overview,
                movieDetailDS.recommended);
    }

    public static MovieDetailDS convert(MovieDetailModel detailModel) {
        return new MovieDetailDS(
                new MovieBasicDS(
                        detailModel.basicModel.id,
                        detailModel.basicModel.title,
                        detailModel.basicModel.image),
                detailModel.backgroundImage,
                detailModel.voteCount,
                detailModel.voteAverage,
                detailModel.releaseDate,
                detailModel.overview,
                detailModel.recommended
        );
    }

    public static List<MovieBasicModel> convert(List<MovieBasicDS> movieBasicDS) {
        List<MovieBasicModel> list = new ArrayList<>();
        for (MovieBasicDS item : movieBasicDS) list.add(convert(item));
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