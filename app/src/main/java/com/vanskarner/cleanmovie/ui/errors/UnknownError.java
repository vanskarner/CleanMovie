package com.vanskarner.cleanmovie.ui.errors;

import com.vanskarner.cleanmovie.R;
import com.vanskarner.cleanmovie.databinding.CommonErrorDialogBinding;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class UnknownError implements ErrorView<CommonErrorDialogBinding> {
    private final ErrorDialog errorDialog;

    @Inject
    public UnknownError(ErrorDialog errorDialog) {
        this.errorDialog = errorDialog;
    }

    @Override
    public CommonErrorDialogBinding setupView(Runnable action) {
        CommonErrorDialogBinding binding = CommonErrorDialogBinding
                .inflate(errorDialog.getLayoutInflater());
        binding.ivError.setImageResource(R.drawable.ic_unknown_error);
        binding.tvMsgError.setText(R.string.msg_unknown_error);
        binding.errorButton.setOnClickListener(v -> action.run());
        return binding;
    }

}