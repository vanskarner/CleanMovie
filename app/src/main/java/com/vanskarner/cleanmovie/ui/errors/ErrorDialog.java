package com.vanskarner.cleanmovie.ui.errors;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.vanskarner.cleanmovie.ui.errors.types.ErrorView;

import javax.inject.Inject;
import javax.inject.Singleton;

@SuppressWarnings("unchecked")
@Singleton
public class ErrorDialog extends DialogFragment {

    private static final String TAG = "ErrorDialog";

    private ErrorView<ViewDataBinding> errorView;
    private Runnable action;
    private ViewDataBinding binding;

    @Inject
    public ErrorDialog() {
    }

    public void setError(@NonNull ErrorView<?> errorView,
                         @NonNull Runnable action) {
        this.errorView = (ErrorView<ViewDataBinding>) errorView;
        this.action = action;
    }

    public void show(@NonNull FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = errorView.setupView(action);
        return new AlertDialog.Builder(getLayoutInflater().getContext())
                .setView(binding.getRoot())
                .create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}