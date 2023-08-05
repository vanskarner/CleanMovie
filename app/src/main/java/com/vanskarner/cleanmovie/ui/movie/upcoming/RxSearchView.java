package com.vanskarner.cleanmovie.ui.movie.upcoming;

import androidx.appcompat.widget.SearchView;

import com.vanskarner.cleanmovie.di.qualiers.ViewQualifiers;
import com.vanskarner.cleanmovie.di.scopes.PerFragment;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

@PerFragment
class RxSearchView {

    private static final int TIMEOUT = 350;
    private final Scheduler scheduler;

    @Inject
    public RxSearchView(@ViewQualifiers.ComputingThread Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    Disposable setFilter(SearchView searchView, Consumer<String> consumer) {
        return getRxOnQueryTextListener(searchView)
                .debounce(TIMEOUT, TimeUnit.MILLISECONDS, scheduler)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    private Observable<String> getRxOnQueryTextListener(SearchView searchView) {
        final PublishSubject<String> subject = PublishSubject.create();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                subject.onNext(text);
                return false;
            }
        });
        return subject;
    }

}