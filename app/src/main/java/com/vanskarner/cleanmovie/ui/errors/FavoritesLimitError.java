package com.vanskarner.cleanmovie.ui.errors;

import androidx.core.content.ContextCompat;

import com.vanskarner.cleanmovie.R;
import com.vanskarner.cleanmovie.databinding.PremiumErrorDialogBinding;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FavoritesLimitError implements ErrorView<PremiumErrorDialogBinding> {
    private final ErrorDialog errorDialog;

    @Inject
    public FavoritesLimitError(ErrorDialog errorDialog) {
        this.errorDialog = errorDialog;
    }

    @Override
    public PremiumErrorDialogBinding setupView(Runnable action) {
        PremiumErrorDialogBinding binding = PremiumErrorDialogBinding
                .inflate(errorDialog.getLayoutInflater());
        int color = ContextCompat.getColor(binding.getRoot().getContext(), R.color.gold);
        binding.localSaveServiceView.setBackgroundColor(color);
        binding.acceptBtn.setOnClickListener(v -> action.run());
        return binding;
    }

}