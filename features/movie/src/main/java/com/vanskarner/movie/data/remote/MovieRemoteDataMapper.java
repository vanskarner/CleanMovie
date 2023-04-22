package com.vanskarner.movie.data.remote;

import com.vanskarner.movie.domain.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;

class MovieRemoteDataMapper {

    private MovieRemoteDataMapper() {
    }

    static MovieBO convert(MovieDTO movieDTO) {
        return new MovieBO(
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

    static List<MovieBO> convert(List<MovieDTO> inputList) {
        List<MovieBO> movieBOS = new ArrayList<>();
        for (MovieDTO item : inputList) movieBOS.add(convert(item));
        return movieBOS;
    }

}