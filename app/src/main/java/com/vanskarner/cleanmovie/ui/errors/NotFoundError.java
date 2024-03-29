package com.vanskarner.cleanmovie.ui.errors;

import com.vanskarner.cleanmovie.R;
import com.vanskarner.cleanmovie.databinding.CommonErrorDialogBinding;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class NotFoundError implements ErrorView<CommonErrorDialogBinding> {
    private final ErrorDialog errorDialog;

    @Inject
    public NotFoundError(ErrorDialog errorDialog) {
        this.errorDialog = errorDialog;
    }

    @Override
    public CommonErrorDialogBinding setupView(Runnable action) {
        CommonErrorDialogBinding binding = CommonErrorDialogBinding
                .inflate(errorDialog.getLayoutInflater());
        binding.ivError.setImageResource(R.drawable.ic_not_found);
        binding.tvMsgError.setText(R.string.msg_not_found);
        binding.errorButton.setOnClickListener(v -> action.run());
        return binding;
    }

}