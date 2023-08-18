package com.vanskarner.core.sync;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.io.IOException;

public class ResultTest {
    String INITIAL_VALUE = "result";
    Result<String> resultSuccess = Result.success(INITIAL_VALUE);

    @Test
    public void map_usingUpperCase_returnSameString() throws Exception {
        Result<String> result = resultSuccess
                .map(String::toUpperCase);
        String actualString = result.get();
        String expectedString = INITIAL_VALUE.toUpperCase();

        assertEquals(expectedString, actualString);
    }

    @Test
    public void flatMap_usingUpperCase_returnSameString() throws Exception {
        Result<String> result = resultSuccess
                .flatMap(s -> Result.success(s.toUpperCase()));
        String actualString = result.get();
        String expectedString = INITIAL_VALUE.toUpperCase();

        assertEquals(expectedString, actualString);
    }

    @Test(expected = RuntimeException.class)
    public void map_causeUncheckedException_throwSameException() throws Exception {
        resultSuccess
                .map(s -> {
                    throw new RuntimeException();
                })
                .get();
    }

    @Test(expected = RuntimeException.class)
    public void flatMap_causeUncheckedException_throwSameException() throws Exception {
        resultSuccess
                .flatMap(o -> Result.failure(new RuntimeException()))
                .get();
    }

    @Test(expected = IOException.class)
    public void map_causeCheckedException_throwSameException() throws Exception {
        resultSuccess
                .map(s -> {
                    throw new IOException();
                })
                .get();
    }

    @Test(expected = IOException.class)
    public void flatMap_causeCheckedException_throwSameException() throws Exception {
        resultSuccess
                .flatMap(o -> Result.failure(new IOException()))
                .get();
    }

    @Test(expected = RuntimeException.class)
    public void mapFlatMap_initialExceptionPropagation_throwInitialException() throws Exception {
        resultSuccess
                .map(s -> {
                    throw new RuntimeException();
                })
                .flatMap(o -> Result.failure(new IOException()))
                .get();
    }

    @Test(expected = IOException.class)
    public void flatMapMap_initialExceptionPropagation_throwInitialException() throws Exception {
        resultSuccess
                .flatMap(s -> Result.failure(new IOException()))
                .map(o -> {
                    throw new RuntimeException();
                })
                .get();
    }

    @Test(expected = NullPointerException.class)
    public void map_withNullFunction_throwNullPointerException() throws Exception {
        resultSuccess
                .map(null)
                .get();
    }

    @Test(expected = NullPointerException.class)
    public void flatMap_withNullFunction_throwNullPointerException() throws Exception {
        resultSuccess
                .flatMap(null)
                .get();
    }

    @Test(expected = NullPointerException.class)
    public void success_withNullValue_throwNullPointerException() throws Exception {
        Result.success(null)
                .get();
    }

    @Test(expected = NullPointerException.class)
    public void failure_withNullValue_throwNullPointerException() throws Exception {
        Result.failure(null)
                .get();
    }

    @Test
    public void map_whenFunctionReturnsNull_mustMatchErrorMessage() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> resultSuccess
                        .map(s -> null)
                        .get());
        String errorMessage = exception.getMessage();

        assertEquals("The map function has returned a null value", errorMessage);
    }

    @Test
    public void flatMap_whenFunctionReturnsNull_mustMatchErrorMessage() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> resultSuccess
                        .flatMap(s -> null)
                        .get());
        String errorMessage = exception.getMessage();

        assertEquals("The flatmap function has returned a null Result", errorMessage);
    }

}