package com.vanskarner.movie.businesslogic.services;

import com.vanskarner.movie.businesslogic.ds.MovieBasicDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;

final class MovieMapper {

    private MovieMapper() {
    }

    public static MovieDetailDS convert(MovieBO movieBO) {
        return new MovieDetailDS(
                new MovieBasicDS(
                        movieBO.getId(),
                        movieBO.getTitle(),
                        movieBO.getImage()),
                movieBO.getBackgroundImage(),
                movieBO.getVoteCount(),
                movieBO.getVoteAverage(),
                movieBO.getReleaseDate(),
                movieBO.getOverview());
    }

    public static MovieBO convert(MovieDetailDS detailDS) {
        return new MovieBO(
                detailDS.movieBasicDS.id,
                detailDS.movieBasicDS.title,
                detailDS.movieBasicDS.image,
                detailDS.backgroundImage,
                detailDS.voteCount,
                detailDS.voteAverage,
                detailDS.releaseDate,
                detailDS.overview);
    }

    public static MoviesDS convert(List<MovieBO> movieBO) {
        List<MovieBasicDS> list = new ArrayList<>();
        for (MovieBO item : movieBO) list.add(toDS(item));
        return new MoviesDS(list);
    }

    private static MovieBasicDS toDS(MovieBO item) {
        return new MovieBasicDS(item.getId(), item.getTitle(), item.getImage());
    }

}