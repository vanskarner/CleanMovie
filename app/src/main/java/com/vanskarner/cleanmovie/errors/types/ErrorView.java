package com.vanskarner.cleanmovie.errors.types;

public interface ErrorView<T> {

    T setupView(Runnable action);

}