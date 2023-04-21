package com.vanskarner.core.sync;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.io.IOException;

public class ResultTest {
    String INCOMING_VALUE = "result";
    String EXPECTED_VALUE = INCOMING_VALUE.toUpperCase();
    Result<String> resultSuccess = Result.success(INCOMING_VALUE);

    @Test
    public void map_incomingString_sameUppercaseString() throws Exception {
        Result<String> result = resultSuccess
                .map(s -> EXPECTED_VALUE);

        assertEquals(EXPECTED_VALUE, result.get());
    }

    @Test
    public void flatMap_incomingString_sameUppercaseString() throws Exception {
        Result<String> result = resultSuccess
                .flatMap(s -> Result.success(EXPECTED_VALUE));

        assertEquals(EXPECTED_VALUE, result.get());
    }

    @Test(expected = RuntimeException.class)
    public void map_uncheckedException_sameException() throws Exception {
        resultSuccess
                .map(s -> {
                    throw new RuntimeException();
                })
                .get();
    }

    @Test(expected = RuntimeException.class)
    public void flatMap_uncheckedException_sameException() throws Exception {
        resultSuccess
                .flatMap(o -> Result.failure(new RuntimeException()))
                .get();
    }

    @Test(expected = IOException.class)
    public void map_checkedException_sameException() throws Exception {
        resultSuccess
                .map(s -> {
                    throw new IOException();
                })
                .get();
    }

    @Test(expected = IOException.class)
    public void flatMap_checkedException_sameException() throws Exception {
        resultSuccess
                .flatMap(o -> Result.failure(new IOException()))
                .get();
    }

    @Test(expected = RuntimeException.class)
    public void mapFlatMap_initialExceptionPropagation_initialException() throws Exception {
        resultSuccess
                .map(s -> {
                    throw new RuntimeException();
                })
                .flatMap(o -> Result.failure(new IOException()))
                .get();
    }

    @Test(expected = IOException.class)
    public void flatMapMap_initialExceptionPropagation_initialException() throws Exception {
        resultSuccess
                .flatMap(s -> Result.failure(new IOException()))
                .map(o -> {
                    throw new RuntimeException();
                })
                .get();
    }

    @Test(expected = NullPointerException.class)
    public void map_nullFunction_nullPointerException() throws Exception {
        resultSuccess
                .map(null)
                .get();
    }

    @Test(expected = NullPointerException.class)
    public void flatMap_nullFunction_nullPointerException() throws Exception {
        resultSuccess
                .flatMap(null)
                .get();
    }

    @Test(expected = NullPointerException.class)
    public void success_nullValue_nullPointerException() throws Exception {
        Result.success(null)
                .get();
    }

    @Test(expected = NullPointerException.class)
    public void failure_nullValue_nullPointerException() throws Exception {
        Result.failure(null)
                .get();
    }

    @Test
    public void map_functionReturnsNull_errorMessageMatch() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> resultSuccess
                        .map(s -> null)
                        .get());
        String errorMessage = exception.getMessage();

        assertEquals("The map function has returned a null value", errorMessage);
    }

    @Test
    public void flatMap_functionReturnsNull_errorMessageMatch() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> resultSuccess
                        .flatMap(s -> null)
                        .get());
        String errorMessage = exception.getMessage();

        assertEquals("The flatmap function has returned a null Result", errorMessage);
    }

}