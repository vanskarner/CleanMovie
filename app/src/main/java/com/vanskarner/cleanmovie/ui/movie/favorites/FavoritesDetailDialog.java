package com.vanskarner.cleanmovie.ui.movie.favorites;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.vanskarner.cleanmovie.databinding.FavoriteDetailDialogBinding;
import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.cleanmovie.ui.movie.MovieDetailModel;

import javax.inject.Inject;

@PerFragment
public class FavoritesDetailDialog extends DialogFragment {

    private static final String TAG = "FavoritesDetailDialog";
    private MovieDetailModel model;

    @Inject
    public FavoritesDetailDialog() {
    }

    public void setModel(@NonNull MovieDetailModel model) {
        this.model = model;
    }

    public void show(@NonNull FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        FavoriteDetailDialogBinding binding =
                FavoriteDetailDialogBinding.inflate(getLayoutInflater());
        binding.setMovieDetail(model);
        return new AlertDialog.Builder(getLayoutInflater().getContext())
                .setView(binding.getRoot())
                .create();
    }

}