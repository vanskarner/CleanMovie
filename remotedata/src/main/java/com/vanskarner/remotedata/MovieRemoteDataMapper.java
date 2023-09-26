package com.vanskarner.remotedata;

import com.vanskarner.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;

final class MovieRemoteDataMapper {

    private MovieRemoteDataMapper() {
    }

    public static MovieBO convert(MovieDTO movieDTO) {
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

    public static List<MovieBO> convert(List<MovieDTO> inputList) {
        List<MovieBO> movieBOS = new ArrayList<>();
        for (MovieDTO item : inputList) movieBOS.add(convert(item));
        return movieBOS;
    }

}