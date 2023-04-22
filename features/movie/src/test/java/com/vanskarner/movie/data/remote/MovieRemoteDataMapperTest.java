package com.vanskarner.movie.data.remote;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.domain.entities.MovieBO;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieRemoteDataMapperTest {
    MovieDTO expectedDTO = new MovieDTO(
            1, "Any overview", "/image.jpg",
            "/background_image.jpg", "2023-01-30", "Clean Movie DTO",
            105, 7.2f
    );

    @Test
    public void convert_fromMovieDetailDTO_toMovieDetailBO() {
        MovieBO actualBO = MovieRemoteDataMapper.convert(expectedDTO);

        assertEquals(expectedDTO.id, actualBO.getId());
        assertEquals(expectedDTO.title, actualBO.getTitle());
        assertEquals(expectedDTO.posterPath, actualBO.getImage());
        assertEquals(expectedDTO.backdropPath, actualBO.getBackgroundImage());
        assertEquals(expectedDTO.voteCount, actualBO.getVoteCount());
        assertEquals(expectedDTO.voteAverage, actualBO.getVoteAverage(), 0.01);
        assertEquals(expectedDTO.releaseDate, actualBO.getReleaseDate());
        assertEquals(expectedDTO.overview, actualBO.getOverview());
    }

    @Test
    public void convert_fromListMovieDetailDTO_toListMovieDetailBO() {
        List<MovieDTO> expectedDTO = new ArrayList<>(
                Collections.singletonList(this.expectedDTO));
        List<MovieBO> actualBO = MovieRemoteDataMapper.convert(expectedDTO);

        assertEquals(expectedDTO.size(), actualBO.size());
        assertEquals(expectedDTO.get(0).id, actualBO.get(0).getId());
        assertEquals(expectedDTO.get(0).title, actualBO.get(0).getTitle());
        assertEquals(expectedDTO.get(0).posterPath, actualBO.get(0).getImage());
        assertEquals(expectedDTO.get(0).backdropPath, actualBO.get(0).getBackgroundImage());
        assertEquals(expectedDTO.get(0).voteCount, actualBO.get(0).getVoteCount());
        assertEquals(expectedDTO.get(0).voteAverage, actualBO.get(0).getVoteAverage(), 0.01);
        assertEquals(expectedDTO.get(0).releaseDate, actualBO.get(0).getReleaseDate());
        assertEquals(expectedDTO.get(0).overview, actualBO.get(0).getOverview());
    }

}