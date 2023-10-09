package com.vanskarner.movie.businesslogic;

abstract class UseCase<OutPut, Input> {
    public abstract OutPut execute(Input input);
}