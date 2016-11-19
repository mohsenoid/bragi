package com.mirhoseini.bragi.numbers;


import com.mirhoseini.bragi.domain.NumbersResponse;
import com.mirhoseini.bragi.domain.client.BragiApi;
import com.mirhoseini.bragi.util.SchedulerProvider;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subjects.ReplaySubject;

/**
 * Created by Mohsen on 18/11/2016.
 */

@Numbers
class NumbersInteractorImpl implements NumbersInteractor {

    private BragiApi api;
    private SchedulerProvider scheduler;

    private ReplaySubject<NumbersResponse> numbersSubject;
    private Subscription numbersSubscription;

    @Inject
    public NumbersInteractorImpl(BragiApi api, SchedulerProvider scheduler) {
        this.api = api;
        this.scheduler = scheduler;
    }

    @Override
    public Observable<NumbersResponse> getNumbers() {
        if (numbersSubscription == null || numbersSubscription.isUnsubscribed()) {
            numbersSubject = ReplaySubject.create();

            numbersSubscription = api.getNumbers()
                    .subscribeOn(scheduler.backgroundThread())
                    .subscribe(numbersSubject);
        }

        return numbersSubject.asObservable();
    }


    @Override
    public void unbind() {
        if (numbersSubscription != null && !numbersSubscription.isUnsubscribed())
            numbersSubscription.unsubscribe();
    }

}
