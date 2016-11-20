package com.mirhoseini.bragi.numbers;

import com.mirhoseini.bragi.domain.client.BragiApi;
import com.mirhoseini.bragi.util.SchedulerProvider;

import net.bragi.foo.model.NumbersResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class NumbersInteractorTest {

    private final static int TEST_NUMBER = 140;
    private NumbersInteractor interactor;
    private NumbersResponse expectedResult;

    @Before
    public void setup() {
        BragiApi api = mock(BragiApi.class);
        SchedulerProvider scheduler = mock(SchedulerProvider.class);

        // Set up the stub we want to return in the mock
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(TEST_NUMBER);

        // put the test data in a test api result
        expectedResult = new NumbersResponse(numbers);

        // mock scheduler to run immediately
        when(scheduler.mainThread())
                .thenReturn(Schedulers.immediate());
        when(scheduler.backgroundThread())
                .thenReturn(Schedulers.immediate());

        // mock api result with expected result
        when(api.getNumbers())
                .thenReturn(Observable.just(expectedResult));

        // create a real new interactor using mocked api and scheduler
        interactor = new NumbersInteractorImpl(api, scheduler);
    }

    @Test
    public void testLoadNumbers() throws Exception {
        TestSubscriber<NumbersResponse> testSubscriber = new TestSubscriber<>();

        // call interactor with some random params
        interactor.getNumbers()
                .subscribe(testSubscriber);

        // it must return the expectedResult with no error
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(expectedResult));
    }
}
