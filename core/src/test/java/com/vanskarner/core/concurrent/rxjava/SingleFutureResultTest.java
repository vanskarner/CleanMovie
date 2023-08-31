package com.vanskarner.core.concurrent.rxjava;

import static org.junit.Assert.assertEquals;

import com.vanskarner.core.concurrent.FutureResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SingleFutureResultTest {
    static CompositeDisposable compositeDisposable = new CompositeDisposable();
    static String INITIAL_VALUE = "AnyString";
    FutureResult<String> futureResult;

    @Before
    public void setUp() {
        Scheduler testScheduler = Schedulers.trampoline();
        RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable,
                testScheduler, testScheduler);
        futureResult = rxFutureFactory.fromSingle(Single.just(INITIAL_VALUE));
    }

    @After
    public void tearDown() {
        compositeDisposable.clear();
    }

    @Test
    public void map_usingUpperCase_returnSameString() throws Exception {
        String actualString = futureResult.map(String::toUpperCase).get();
        String expectedString = INITIAL_VALUE.toUpperCase();

        assertEquals(expectedString, actualString);
    }

    @Test
    public void flatMap_usingMapForUpperCase_returnSameString() throws Exception {
        String actualString = futureResult
                .flatMap(s -> futureResult.map(s1 -> INITIAL_VALUE.toUpperCase())).get();
        String expectedString = INITIAL_VALUE.toUpperCase();

        assertEquals(expectedString, actualString);
    }

    @Test(expected = NullPointerException.class)
    public void map_withNullFunction_throwNullPointerException() throws Exception {
        futureResult.map(null).get();
    }

    @Test(expected = NullPointerException.class)
    public void flatMap_withNullFunction_throwNullPointerException() throws Exception {
        futureResult.flatMap(null).get();
    }

}