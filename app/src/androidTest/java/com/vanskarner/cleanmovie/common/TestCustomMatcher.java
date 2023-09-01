package com.vanskarner.cleanmovie.common;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedDiagnosingMatcher;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Objects;

public class TestCustomMatcher {

    public static Matcher<View> withRecyclerViewItemCount(final int expectedCount) {
        return new BoundedDiagnosingMatcher<View, RecyclerView>(RecyclerView.class) {
            @SuppressWarnings("rawtypes")
            @Override
            protected boolean matchesSafely(RecyclerView recyclerView, Description mismatchDescription) {
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                int itemCount = Objects.requireNonNull(adapter).getItemCount();
                mismatchDescription
                        .appendText("adapter.getItemCount() was: ")
                        .appendValue(itemCount);
                return itemCount == expectedCount;
            }

            @Override
            protected void describeMoreTo(Description description) {
                description.appendText("adapter.getItemCount() to be: " + expectedCount);
            }
        };
    }

    public static Matcher<View> withImageDrawable(final int resourceId) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with drawable from resource id: ");
                description.appendValue(resourceId);
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                Resources resources = imageView.getContext().getResources();
                Drawable expected = ResourcesCompat.getDrawable(resources, resourceId, null);
                Drawable actual = imageView.getDrawable();
                return isSame(actual, expected);
            }
        };
    }

    public static Matcher<View> withActionIconDrawable(@DrawableRes final int resourceId) {
        return new BoundedMatcher<View, ActionMenuItemView>(ActionMenuItemView.class) {
            @Override
            public void describeTo(final Description description) {
                description.appendText("has image drawable resource " + resourceId);
            }

            @Override
            public boolean matchesSafely(final ActionMenuItemView actionMenuItemView) {
                Resources resources = actionMenuItemView.getContext().getResources();
                Drawable expected = ResourcesCompat.getDrawable(resources, resourceId, null);
                Drawable actual = actionMenuItemView.getItemData().getIcon();
                return isSame(actual, expected);
            }
        };
    }

    private static boolean isSame(Drawable actual, Drawable expected) {
        Bitmap bitmapActual = toBitmap(actual);
        Bitmap bitmapExpected = toBitmap(expected);
        return bitmapExpected.sameAs(bitmapActual);
    }

    private static Bitmap toBitmap(Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        Drawable drawableCopy = constantState.newDrawable();
        int width = drawableCopy.getIntrinsicWidth();
        int height = drawableCopy.getIntrinsicHeight();
        if (width <= 0) width = 1;
        if (height <= 0) height = 1;
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        drawableCopy.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawableCopy.draw(canvas);
        return result;
    }

}