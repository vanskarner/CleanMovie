package com.vanskarner.cleanmovie.errors.types;

import com.vanskarner.cleanmovie.databinding.CommonErrorDialogBinding;
import com.vanskarner.cleanmovie.errors.ErrorDialog;
import com.vanskarner.cleanmovie.R;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoInternetError implements ErrorView<CommonErrorDialogBinding> {
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