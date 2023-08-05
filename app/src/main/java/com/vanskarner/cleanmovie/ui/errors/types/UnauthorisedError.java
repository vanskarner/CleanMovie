package com.vanskarner.cleanmovie.ui.errors.types;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.vanskarner.cleanmovie.R;
import com.vanskarner.cleanmovie.databinding.CommonErrorDialogBinding;
import com.vanskarner.cleanmovie.ui.errors.ErrorDialog;

@Singleton
public class UnauthorisedError implements ErrorView<CommonErrorDialogBinding> {
    private final ErrorDialog errorDialog;

    @Inject
    public UnauthorisedError(ErrorDialog errorDialog) {
        this.errorDialog = errorDialog;
    }

    @Override
    public CommonErrorDialogBinding setupView(Runnable action) {
        CommonErrorDialogBinding binding = CommonErrorDialogBinding
                .inflate(errorDialog.getLayoutInflater());
        binding.ivError.setImageResource(R.drawable.ic_unauthorised);
        binding.tvMsgError.setText(R.string.msg_unauthorized);
        binding.errorButton.setOnClickListener(v -> action.run());
        return binding;
    }

}