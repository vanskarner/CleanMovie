package com.vanskarner.cleanmovie.ui.errors;

public interface ErrorView<T> {

    T setupView(Runnable action);

}