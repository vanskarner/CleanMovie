package com.vanskarner.movie.persistence.remote;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;

import java.util.ArrayList;
import java.util.List;

final class MovieRemoteDataMapper {

    private MovieRemoteDataMapper() {
    }

    static MovieDetailDS convert(MovieDTO movieDTO) {
        return new MovieDetailDS(
                movieDTO.id,
                movieDTO.title,
                movieDTO.posterPath,
                movieDTO.backdropPath,
                movieDTO.voteCount,
                movieDTO.voteAverage,
                movieDTO.releaseDate,
                movieDTO.overview
        );
    }

    static MoviesDS convert(List<MovieDTO> inputList) {
        List<MovieDS> movieBOS = new ArrayList<>();
        for (MovieDTO item : inputList)
            movieBOS.add(new MovieDS(item.id, item.title, item.posterPath));
        return new MoviesDS(movieBOS);
    }

}