package com.vanskarner.movie.persistence.remote;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.entities.MovieBO;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieRemoteDataMapperTest {
    static MovieDTO dataStructure;

    @BeforeClass
    public static void setup() {
        dataStructure = new MovieDTO(1,
                "Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time.",
                "/anyImage.jpg",
                "/anyBackImage.jpg",
                "2023-08-15",
                "Clean Architecture",
                100,
                9.5f);

    }

    @Test
    public void convert_fromMovieDetailDTO_toMovieDetailBO() {
        MovieBO actualItem = MovieRemoteDataMapper.convert(dataStructure);
        MovieDTO expectedItem = dataStructure;

        assertEquals(expectedItem.id, actualItem.getId());
        assertEquals(expectedItem.title, actualItem.getTitle());
        assertEquals(expectedItem.posterPath, actualItem.getImage());
        assertEquals(expectedItem.backdropPath, actualItem.getBackgroundImage());
        assertEquals(expectedItem.voteCount, actualItem.getVoteCount());
        assertEquals(expectedItem.voteAverage, actualItem.getVoteAverage(), 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.getReleaseDate());
        assertEquals(expectedItem.overview, actualItem.getOverview());
    }

    @Test
    public void convert_fromListMovieDetailDTO_toListMovieDetailBO() {
        List<MovieDTO> expectedList = new ArrayList<>(Collections.singletonList(dataStructure));
        List<MovieBO> actualList = MovieRemoteDataMapper.convert(expectedList);

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).id, actualList.get(0).getId());
        assertEquals(expectedList.get(0).title, actualList.get(0).getTitle());
        assertEquals(expectedList.get(0).posterPath, actualList.get(0).getImage());
        assertEquals(expectedList.get(0).backdropPath, actualList.get(0).getBackgroundImage());
        assertEquals(expectedList.get(0).voteCount, actualList.get(0).getVoteCount());
        assertEquals(expectedList.get(0).voteAverage, actualList.get(0).getVoteAverage(), 0.01);
        assertEquals(expectedList.get(0).releaseDate, actualList.get(0).getReleaseDate());
        assertEquals(expectedList.get(0).overview, actualList.get(0).getOverview());
    }

}