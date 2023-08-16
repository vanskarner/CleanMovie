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
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String EXPECTED_VALUE = "CompletableFutureSimpleResult";
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
    public void toFutureResult_withString_SameString() throws Exception {
        String actual = futureResult.toFutureResult(EXPECTED_VALUE).get();

        assertEquals(EXPECTED_VALUE, actual);
    }

    @Test(expected = NullPointerException.class)
    public void toFutureResult_nullValue_nullPointerException() throws Exception {
        futureResult.toFutureResult(null).get();
    }
}