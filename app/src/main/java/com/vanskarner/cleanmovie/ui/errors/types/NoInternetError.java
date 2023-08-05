package com.vanskarner.cleanmovie.ui.errors.types;

import com.vanskarner.cleanmovie.databinding.CommonErrorDialogBinding;
import com.vanskarner.cleanmovie.ui.errors.ErrorDialog;
import com.vanskarner.cleanmovie.R;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class NoInternetError implements ErrorView<CommonErrorDialogBinding> {
    private final ErrorDialog errorDialog;

    @Inject
    public NoInternetError(ErrorDialog errorDialog) {
        this.errorDialog = errorDialog;
    }

    @Override
    public CommonErrorDialogBinding setupView(Runnable action) {
        CommonErrorDialogBinding binding = CommonErrorDialogBinding
                .inflate(errorDialog.getLayoutInflater());
        binding.ivError.setImageResource(R.drawable.ic_no_internet);
        binding.tvMsgError.setText(R.string.msg_no_internet);
        binding.errorButton.setOnClickListener(v -> action.run());
        return binding;
    }

}