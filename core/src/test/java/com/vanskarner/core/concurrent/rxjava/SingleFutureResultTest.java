package com.vanskarner.core.concurrent.rxjava;

import static org.junit.Assert.assertEquals;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.concurrent.rxjava.DefaultRxFutureFactory;
import com.vanskarner.core.concurrent.rxjava.RxFutureFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SingleFutureResultTest {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String EXPECTED_VALUE = "SingleFutureResult";
    FutureResult<String> futureResult;

    @Before
    public void setUp() {
        Scheduler testScheduler = Schedulers.trampoline();
        RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable,
                testScheduler, testScheduler);
        futureResult = rxFutureFactory.fromSingle(Single.just(EXPECTED_VALUE));
    }

    @After
    public void tearDown() {
        compositeDisposable.clear();
    }

    @Test
    public void map_incomingString_sameUppercaseString() {
        String actual = futureResult.map(String::toUpperCase).get();

        assertEquals(EXPECTED_VALUE.toUpperCase(), actual);
    }

    @Test
    public void flatMap_incomingString_sameUppercaseString() {
        String actual = futureResult
                .flatMap(s -> futureResult.map(s1 -> EXPECTED_VALUE.toUpperCase())).get();

        assertEquals(EXPECTED_VALUE.toUpperCase(), actual);
    }

    @Test(expected = NullPointerException.class)
    public void map_nullFunction_nullPointerException() {
        futureResult.map(null).get();
    }

    @Test(expected = NullPointerException.class)
    public void flatMap_nullFunction_nullPointerException() {
        futureResult.flatMap(null).get();
    }

}