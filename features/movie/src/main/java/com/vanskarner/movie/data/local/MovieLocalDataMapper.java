package com.vanskarner.movie.data.local;

import com.vanskarner.movie.domain.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;

class MovieLocalDataMapper {

    private MovieLocalDataMapper() {
    }

    static MovieBO convert(MovieEntity movieEntity) {
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

    static MovieEntity convert(MovieBO movieBO) {
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

    static List<MovieBO> convert(List<MovieEntity> movieBillboardEntities) {
        List<MovieBO> movieBOS = new ArrayList<>();
        for (MovieEntity item : movieBillboardEntities) {
            movieBOS.add(convert(item));
        }
        return movieBOS;
    }

}