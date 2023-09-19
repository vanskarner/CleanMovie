package com.vanskarner.movie.persistence.remote;

import com.vanskarner.movie.businesslogic.ds.MovieBasicDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;

import java.util.ArrayList;
import java.util.List;

final class MovieRemoteDataMapper {

    private MovieRemoteDataMapper() {
    }

    static MovieDetailDS convert(MovieDTO movieDTO) {
        return new MovieDetailDS(
                new MovieBasicDS(
                        movieDTO.id,
                        movieDTO.title,
                        movieDTO.posterPath),
                movieDTO.backdropPath,
                movieDTO.voteCount,
                movieDTO.voteAverage,
                movieDTO.releaseDate,
                movieDTO.overview
        );
    }

    static MoviesDS convert(List<MovieDTO> inputList) {
        List<MovieBasicDS> movieBOS = new ArrayList<>();
        for (MovieDTO item : inputList)
            movieBOS.add(new MovieBasicDS(item.id, item.title, item.posterPath));
        return new MoviesDS(movieBOS);
    }

}