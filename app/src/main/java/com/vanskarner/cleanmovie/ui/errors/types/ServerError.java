package com.vanskarner.cleanmovie.ui.errors.types;

import com.vanskarner.cleanmovie.databinding.CommonErrorDialogBinding;
import com.vanskarner.cleanmovie.ui.errors.ErrorDialog;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.vanskarner.cleanmovie.R;

@Singleton
public class ServerError implements ErrorView<CommonErrorDialogBinding> {
    private final ErrorDialog errorDialog;

    @Inject
    public ServerError(ErrorDialog errorDialog) {
        this.errorDialog = errorDialog;
    }

    @Override
    public CommonErrorDialogBinding setupView(Runnable action) {
        CommonErrorDialogBinding binding = CommonErrorDialogBinding
                .inflate(errorDialog.getLayoutInflater());
        binding.ivError.setImageResource(R.drawable.ic_server_error);
        binding.tvMsgError.setText(R.string.msg_server_error);
        binding.errorButton.setOnClickListener(v -> action.run());
        return binding;
    }

}