package com.vanskarner.cleanmovie.ui.movie;

import com.vanskarner.usecases.movie.service.MovieBasicDS;
import com.vanskarner.usecases.movie.service.MovieDetailDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MovieDetailDSMother {

    public static MovieDetailDS createSample() throws IOException {
        return create(1);
    }

    public static MovieDetailDS createSampleWith(int id) throws IOException {
        return create(id);
    }

    private static MovieDetailDS create(int id) throws IOException {
        String base64Image = loadSampleImageBase64();
        return new MovieDetailDS(
                new MovieBasicDS(id,
                        "Clean Architecture",
                        base64Image),
                base64Image,
                100,
                9.5f,
                "2023-08-15",
                "Robert C. Martin's Clean Architecture is a guide that emphasizes " +
                        "craftsmanship in software design, promoting modular and maintainable " +
                        "systems through a component and decoupled structure, with the goal of " +
                        "achieving sustainable code over time.");
    }

    private static String loadSampleImageBase64() throws IOException {
        String fileName = "base64_topic_image.txt";
        String receiveString = "";
        ClassLoader classLoader = MovieDetailDSMother.class.getClassLoader();
        InputStream inputStream = Objects.requireNonNull(classLoader).getResourceAsStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(
                Objects.requireNonNull(inputStream));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        while (receiveString != null) {
            stringBuilder.append(receiveString);
            receiveString = bufferedReader.readLine();
        }
        inputStream.close();
        return stringBuilder.toString();
    }

}