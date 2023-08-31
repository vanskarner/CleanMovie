package com.vanskarner.movie.persistence.local;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;

import java.util.ArrayList;
import java.util.List;

class MovieLocalDataMapper {

    private MovieLocalDataMapper() {
    }

    static MovieDetailDS convert(MovieEntity movieEntity) {
        return new MovieDetailDS(
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

    static MovieEntity convert(MovieDetailDS detailDS) {
        return new MovieEntity(
                detailDS.id,
                detailDS.title,
                detailDS.image,
                detailDS.backgroundImage,
                detailDS.voteCount,
                detailDS.voteAverage,
                detailDS.releaseDate,
                detailDS.overview
        );
    }

    static MoviesDS convert(List<MovieEntity> movieEntities) {
        List<MovieDS> list = new ArrayList<>();
        for (MovieEntity entity : movieEntities)
            list.add(new MovieDS(entity.id, entity.title, entity.encodedImage));
        return new MoviesDS(list);
    }

}