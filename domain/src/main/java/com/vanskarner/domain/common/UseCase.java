package com.vanskarner.domain.common;

public abstract class UseCase<Output,Input> {
    public abstract Output execute(Input input);

}