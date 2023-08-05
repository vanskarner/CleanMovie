package com.vanskarner.cleanmovie.ui.errors.types;

public interface ErrorView<T> {

    T setupView(Runnable action);

}