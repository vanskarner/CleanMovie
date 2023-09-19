package com.vanskarner.movie.persistence.local;

import com.vanskarner.movie.businesslogic.ds.MovieBasicDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;

import java.util.ArrayList;
import java.util.List;

final class MovieLocalDataMapper {

    private MovieLocalDataMapper() {
    }

    static MovieDetailDS convert(MovieEntity movieEntity) {
        return new MovieDetailDS(
                new MovieBasicDS(
                        movieEntity.id,
                        movieEntity.title,
                        movieEntity.encodedImage),
                movieEntity.encodedBackgroundImage,
                movieEntity.voteCount,
                movieEntity.voteAverage,
                movieEntity.releaseDate,
                movieEntity.overview
        );
    }

    static MovieEntity convert(MovieDetailDS detailDS) {
        return new MovieEntity(
                detailDS.basicInfo.id,
                detailDS.basicInfo.title,
                detailDS.basicInfo.image,
                detailDS.backgroundImage,
                detailDS.voteCount,
                detailDS.voteAverage,
                detailDS.releaseDate,
                detailDS.overview
        );
    }

    static MoviesDS convert(List<MovieEntity> movieEntities) {
        List<MovieBasicDS> list = new ArrayList<>();
        for (MovieEntity entity : movieEntities)
            list.add(new MovieBasicDS(entity.id, entity.title, entity.encodedImage));
        return new MoviesDS(list);
    }

}