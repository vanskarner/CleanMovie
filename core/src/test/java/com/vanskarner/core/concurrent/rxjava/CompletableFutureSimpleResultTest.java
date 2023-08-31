package com.vanskarner.core.concurrent.rxjava;

import static org.junit.Assert.assertEquals;

import com.vanskarner.core.concurrent.FutureSimpleResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CompletableFutureSimpleResultTest {
    static CompositeDisposable compositeDisposable = new CompositeDisposable();
    FutureSimpleResult futureResult;

    @Before
    public void setUp() {
        Scheduler testScheduler = Schedulers.trampoline();
        RxFutureFactory rxFutureFactory = new DefaultRxFutureFactory(compositeDisposable,
                testScheduler, testScheduler);
        futureResult = rxFutureFactory.fromCompletable(Completable.complete());
    }

    @After
    public void tearDown() {
        compositeDisposable.clear();
    }

    @Test
    public void toFutureResult_withString_returnSameString() throws Exception {
        String expectedString = "AnyString";
        String actualString = futureResult.toFutureResult(expectedString).get();

        assertEquals(expectedString, actualString);
    }

    @Test(expected = NullPointerException.class)
    public void toFutureResult_withNullValue_throwNullPointerException() throws Exception {
        futureResult.toFutureResult(null).get();
    }

}