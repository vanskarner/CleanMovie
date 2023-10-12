package com.vanskarner.movie.businesslogic;

abstract class UseCase<Output, Input> {
    public abstract Output execute(Input input);

}