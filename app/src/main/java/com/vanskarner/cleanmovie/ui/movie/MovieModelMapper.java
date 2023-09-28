package com.vanskarner.cleanmovie.ui.movie;

import com.vanskarner.movie.businesslogic.ds.MovieBasicDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;

import java.util.ArrayList;
import java.util.List;

public class MovieModelMapper {

    private MovieModelMapper() {
    }

    public static MovieDetailModel convert(MovieDetailDS movieDetailDS) {
        return new MovieDetailModel(
                new MovieBasicModel(
                        movieDetailDS.movieBasicDS.id,
                        movieDetailDS.movieBasicDS.title,
                        movieDetailDS.movieBasicDS.image),
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

    private static MovieBasicModel convert(MovieBasicDS movieBasicDS) {
        return new MovieBasicModel(movieBasicDS.id, movieBasicDS.title, movieBasicDS.image);
    }

}