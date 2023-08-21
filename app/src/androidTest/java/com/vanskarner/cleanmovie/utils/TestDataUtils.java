package com.vanskarner.cleanmovie.utils;

import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;

public final class TestDataUtils {
    public static MovieDetailDS createMovieDetailWith(int id, String base64Image) {
        return new MovieDetailDS(id,
                "Clean Architecture",
                base64Image,
                base64Image,
                100,
                9.5f,
                "2023-08-15",
                "Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time.");
    }

}