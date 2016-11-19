package com.mirhoseini.bragi.numbers;

import com.mirhoseini.bragi.domain.NumbersResponse;
import com.mirhoseini.bragi.domain.model.DataModel;
import com.mirhoseini.bragi.util.SchedulerProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Mohsen on 18/11/2016.
 */

class NumbersPresenterImpl implements NumbersPresenter {

    @Inject
    NumbersInteractor interactor;
    @Inject
    SchedulerProvider scheduler;

    private NumbersView view;
    private Subscription subscription = Subscriptions.empty();

    @Inject
    public NumbersPresenterImpl() {

    }

    @Override
    public void bind(NumbersView view) {
        this.view = view;
    }


    @Override
    public void loadData(boolean isConnected) {
        if (null != view) {
            view.showProgress();
        }

        subscription = interactor.getNumbers()
                // convert numbers to DataModel
                .map(this::mapNumbersResponseToDataList)
                // sort DataModel
                .map(this::sortDataList)
                // back to the MainThread
                .observeOn(scheduler.mainThread())
                .subscribe(dataList -> {
                            if (null != view) {
                                view.hideProgress();
                                view.setDataList(dataList);

                                if (!isConnected)
                                    view.showOfflineMessage(false);
                            }
                        },
                        // handle exceptions
                        throwable -> {
                            if (null != view) {
                                view.hideProgress();
                            }

                            if (isConnected) {
                                view.showRetryMessage(throwable);
                            } else {
                                if (null != view) {
                                    view.showOfflineMessage(true);
                                }
                            }
                        }

                );
    }

    private List<DataModel> mapNumbersResponseToDataList(NumbersResponse numbersResponse) {
        List<DataModel> dataModel = new ArrayList<>();

        for (Integer number : numbersResponse.getNumbers()) {
            dataModel.add(new DataModel(number));
        }

        return dataModel;
    }

    private ArrayList<DataModel> sortDataList(List<DataModel> dataList) {
        ArrayList<DataModel> result = new ArrayList<>(dataList);

        Collections.sort(result, (dataModel1, dataModel2) -> {
                    if (dataModel1.getSection().getValue() > dataModel2.getSection().getValue())
                        return 1;
                    else if (dataModel1.getSection().getValue() < dataModel2.getSection().getValue())
                        return -1;
                    else {
                        if (dataModel1.getItem().getValue() > dataModel2.getItem().getValue())
                            return 1;
                        else if (dataModel1.getItem().getValue() < dataModel2.getItem().getValue())
                            return -1;
                        else
                            return 0;
                    }
                }
        );

        return result;
    }

    @Override
    public void unbind() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();

        interactor.unbind();

        view = null;
        interactor = null;
    }
}
