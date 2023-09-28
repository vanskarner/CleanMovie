package com.vanskarner.movie.businesslogic.services;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;

class MovieMapper {

    private MovieMapper() {
    }

    public static MovieDetailDS convert(MovieBO movieBO) {
        return new MovieDetailDS(
                movieBO.getId(),
                movieBO.getTitle(),
                movieBO.getImage(),
                movieBO.getBackgroundImage(),
                movieBO.getVoteCount(),
                movieBO.getVoteAverage(),
                movieBO.getReleaseDate(),
                movieBO.getOverview());
    }

    public static MovieBO convert(MovieDetailDS detailDS) {
        return new MovieBO(
                detailDS.id,
                detailDS.title,
                detailDS.image,
                detailDS.backgroundImage,
                detailDS.voteCount,
                detailDS.voteAverage,
                detailDS.releaseDate,
                detailDS.overview);
    }

    public static MoviesDS convert(List<MovieBO> movieBO) {
        List<MovieDS> list = new ArrayList<>();
        for (MovieBO item : movieBO) list.add(toDS(item));
        return new MoviesDS(list);
    }

    private static MovieDS toDS(MovieBO item) {
        return new MovieDS(item.getId(), item.getTitle(), item.getImage());
    }

}