package com.vanskarner.core;

public interface ExceptionFunction<T, R> {

    R apply(T t) throws Exception;

}