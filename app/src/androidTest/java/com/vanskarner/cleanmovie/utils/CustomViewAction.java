package com.vanskarner.cleanmovie.utils;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;

public class CustomViewAction {

    public static ViewAction swipeUpSlow() {
        return new GeneralSwipeAction(
                Swipe.SLOW,
                GeneralLocation.CENTER,
                view -> {
                    float[] coordinates = GeneralLocation.CENTER.calculateCoordinates(view);
                    coordinates[1] = 0;
                    return coordinates;
                },
                Press.FINGER);
    }
}