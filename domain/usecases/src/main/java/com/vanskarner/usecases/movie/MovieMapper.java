package com.vanskarner.usecases.movie;

import com.vanskarner.usecases.movie.service.MovieBasicDS;
import com.vanskarner.usecases.movie.service.MovieDetailDS;
import com.vanskarner.usecases.movie.service.MoviesDS;

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
                detailDS.movieBasic.id,
                detailDS.movieBasic.title,
                detailDS.movieBasic.image,
                detailDS.backgroundImage,
                detailDS.voteCount,
                detailDS.voteAverage,
                detailDS.releaseDate,
                detailDS.overview);
    }

    public static MoviesDS convert(List<MovieBO> movieBO) {
        List<MovieBasicDS> list = new ArrayList<>();
        for (MovieBO item : movieBO)
            list.add(toDS(item));
        return new MoviesDS(list);
    }

    private static MovieBasicDS toDS(MovieBO item) {
        return new MovieBasicDS(item.getId(), item.getTitle(), item.getImage());
    }

}