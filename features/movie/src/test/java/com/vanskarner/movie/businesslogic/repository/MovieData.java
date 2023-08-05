package com.vanskarner.movie.businesslogic.repository;

import com.vanskarner.movie.businesslogic.entities.MovieBO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MovieData {

    public static List<MovieBO> getData() {
        List<MovieBO> list = new ArrayList<>();
        MovieBO movieOne = new MovieBO(1, "Plane", "", "", 100, 10, "", "");
        MovieBO movieTwo = new MovieBO(2, "Puss in Boots: The Last Wish", "", "", 90, 9, "", "");
        MovieBO movieThree = new MovieBO(3, "M3GAN", "", "", 80, 8, "", "");
        MovieBO movieFour = new MovieBO(4, "The Whale", "", "", 75, 7.5f, "", "");
        MovieBO movieFive = new MovieBO(5, "Detective Knight: Independence", "", "", 74, 7.5f, "", "");
        MovieBO movieSix = new MovieBO(6, "Transfusion", "", "", 90, 7.4f, "", "");
        MovieBO movieSeven = new MovieBO(7, "Transfusion", "", "", 60, 9.5f, "", "");
        MovieBO movieEight = new MovieBO(8, "The Price We Pay", "", "", 75, 7, "", "");
        MovieBO movieNine = new MovieBO(9, "Knock at the Cabin", "", "", 74, 7.4f, "", "");
        list.add(movieOne);
        list.add(movieTwo);
        list.add(movieThree);
        list.add(movieFour);
        list.add(movieFive);
        list.add(movieSix);
        list.add(movieSeven);
        list.add(movieEight);
        list.add(movieNine);
        return list;
    }

    public static int getQuantity() {
        return getData().size();
    }

    public static MovieBO getAnyItem() {
        int post = generateRandomNumberFromZero(getData().size() - 1);
        return getData().get(post);
    }

    public static int getInvalidID() {
        return 99;
    }

    private static int generateRandomNumberFromZero(int end) {
        return ThreadLocalRandom.current().nextInt(0, end + 1);
    }

}