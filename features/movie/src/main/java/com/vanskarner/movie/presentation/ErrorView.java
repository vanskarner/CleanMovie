package com.vanskarner.movie.presentation;

public interface ErrorView<T> {

    T setupView(Runnable action);

}