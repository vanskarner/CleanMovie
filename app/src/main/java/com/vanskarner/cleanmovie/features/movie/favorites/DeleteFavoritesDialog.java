package com.vanskarner.cleanmovie.features.movie.favorites;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.vanskarner.cleanmovie.R;
import com.vanskarner.cleanmovie.di.scopes.PerFragment;

import javax.inject.Inject;

@PerFragment
public class DeleteFavoritesDialog extends DialogFragment {

    private static final String TAG = "DeleteFavoritesDialog";

    private DialogInterface.OnClickListener onAccept;

    @Inject
    public DeleteFavoritesDialog() {
    }

    public void setOnAccept(@NonNull DialogInterface.OnClickListener onAccept) {
        this.onAccept = onAccept;
    }

    public void show(@NonNull FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(R.string.msg_delete_favorites)
                .setPositiveButton(R.string.ok, onAccept)
                .setNegativeButton(R.string.cancel, (dialog, i) -> dialog.dismiss())
                .create();
    }

}