package com.vanskarner.usecases.movie;

import com.vanskarner.entities.MovieBO;
import com.vanskarner.usecases.movie.ds.MovieDS;
import com.vanskarner.usecases.movie.ds.MovieDetailDS;
import com.vanskarner.usecases.movie.ds.MoviesDS;

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
        for (MovieBO item : movieBO)
            list.add(new MovieDS(item.getId(), item.getTitle(), item.getImage()));
        return new MoviesDS(list);
    }

}