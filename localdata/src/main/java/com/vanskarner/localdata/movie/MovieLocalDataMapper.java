package com.vanskarner.localdata.movie;

import com.vanskarner.usecases.movie.MovieBO;

import java.util.ArrayList;
import java.util.List;

final class MovieLocalDataMapper {

    private MovieLocalDataMapper() {
    }

    public static MovieBO convert(MovieEntity movieEntity) {
        return new MovieBO(
                movieEntity.id,
                movieEntity.title,
                movieEntity.encodedImage,
                movieEntity.encodedBackgroundImage,
                movieEntity.voteCount,
                movieEntity.voteAverage,
                movieEntity.releaseDate,
                movieEntity.overview
        );
    }

    public static MovieEntity convert(MovieBO movieBO) {
        return new MovieEntity(
                movieBO.getId(),
                movieBO.getTitle(),
                movieBO.getImage(),
                movieBO.getBackgroundImage(),
                movieBO.getVoteCount(),
                movieBO.getVoteAverage(),
                movieBO.getReleaseDate(),
                movieBO.getOverview()
        );
    }

    public static List<MovieBO> convert(List<MovieEntity> movieBillboardEntities) {
        List<MovieBO> movieBOS = new ArrayList<>();
        for (MovieEntity item : movieBillboardEntities) {
            movieBOS.add(convert(item));
        }
        return movieBOS;
    }

}