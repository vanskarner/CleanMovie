package com.vanskarner.cleanmovie.ui;

import android.util.Base64;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.vanskarner.cleanmovie.R;

public class BindingAdapters {

    @BindingAdapter("imageUrl")
    public static void loadImageUrl(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_movie_24)
                .error(R.drawable.ic_error_image_24)
                .into(imageView);
    }

    @BindingAdapter("imageBase64")
    public static void loadImageBase64(ImageView imageView, String imageBase64) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(Base64.decode(imageBase64, Base64.DEFAULT))
                .placeholder(R.drawable.ic_movie_24)
                .into(imageView);
    }

    @BindingAdapter("imageDetailUrl")
    public static void loadImageDetailUrl(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_movie_24)
                .error(R.drawable.ic_movie_background)
                .into(imageView);
    }

    @BindingAdapter("imageDetailBase64")
    public static void loadImageDetailBase64(ImageView imageView, String imageBase64) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(Base64.decode(imageBase64, Base64.DEFAULT))
                .placeholder(R.drawable.ic_movie_background)
                .into(imageView);
    }

}