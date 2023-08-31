package com.vanskarner.movie.persistence.remote;

import static org.junit.Assert.assertEquals;

import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;

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
    public void convert_fromMovieDTO_toMovieDetailDS() {
        MovieDetailDS actualItem = MovieRemoteDataMapper.convert(dataStructure);
        MovieDTO expectedItem = dataStructure;

        assertEquals(expectedItem.id, actualItem.id);
        assertEquals(expectedItem.title, actualItem.title);
        assertEquals(expectedItem.posterPath, actualItem.image);
        assertEquals(expectedItem.backdropPath, actualItem.backgroundImage);
        assertEquals(expectedItem.voteCount, actualItem.voteCount);
        assertEquals(expectedItem.voteAverage, actualItem.voteAverage, 0.01);
        assertEquals(expectedItem.releaseDate, actualItem.releaseDate);
        assertEquals(expectedItem.overview, actualItem.overview);
    }

    @Test
    public void convert_fromListMovieDTO_toListMovieDS() {
        List<MovieDTO> expectedList = new ArrayList<>(Collections.singletonList(dataStructure));
        List<MovieDS> actualList = MovieRemoteDataMapper.convert(expectedList).list;

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).id, actualList.get(0).id);
        assertEquals(expectedList.get(0).title, actualList.get(0).title);
        assertEquals(expectedList.get(0).posterPath, actualList.get(0).image);
    }

}