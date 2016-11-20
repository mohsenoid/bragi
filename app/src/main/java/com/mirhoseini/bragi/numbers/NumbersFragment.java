package com.mirhoseini.bragi.numbers;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mirhoseini.bragi.ApplicationComponent;
import com.mirhoseini.bragi.R;
import com.mirhoseini.bragi.base.BaseFragment;
import com.mirhoseini.bragi.base.BaseView;
import com.mirhoseini.bragi.domain.model.DataModel;
import com.mirhoseini.bragi.numbers.adapter.NumbersRecyclerViewAdapter;
import com.mirhoseini.bragi.numbers.adapter.model.Number;
import com.mirhoseini.utils.Utils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class NumbersFragment extends BaseFragment implements NumbersView {

    private final CompositeSubscription subscriptions = new CompositeSubscription();
    // injecting dependencies via Dagger
    @Inject
    NumbersPresenter presenter;
    @Inject
    Context context;
    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    NumbersRecyclerViewAdapter adapter;
    @Inject
    OnListFragmentInteractionListener listener;
    // injecting views via ButterKnife
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.empty)
    ViewGroup empty;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NumbersFragment() {
    }

    /**
     * This method could be used when we have to pass some Args to fragment using {@link Bundle}
     *
     * @return new instance of NumbersFragment
     */
    public static NumbersFragment newInstance() {
        NumbersFragment fragment = new NumbersFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // subscribe to adapter's records click
        subscriptions.add(
                adapter.asObservable()
                        .filter(number -> null != listener)
                        .subscribe(listener::showData));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_numbers, container, false);

        // inject views using ButterKnife
        ButterKnife.bind(this, view);

        initRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        loadData();
    }

    @Override
    protected void injectDependencies(ApplicationComponent component, Context context) {
        component
                .plus(new AppNumbersModule(context, this))
                .inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;

        presenter.unbind();
        presenter = null;

        subscriptions.unsubscribe();
    }

    @Override
    public void showMessage(String message) {
        if (null != listener) {
            listener.showMessage(message);
        }
    }

    @Override
    public void showOfflineMessage(boolean isForce) {
        if (null != listener) {
            listener.showOfflineMessage(isForce);
        }
    }

    private void initRecyclerView() {
        list.setLayoutManager(layoutManager);
    }

    @Override
    public void setDataList(ArrayList<DataModel> dataList) {
        if (dataList.size() > 0) {
            list.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);

            adapter.setData(dataList);
            list.setAdapter(adapter);
        } else {
            list.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showRetryMessage(Throwable throwable) {
        if (null != listener) {
            listener.showRetryMessage(throwable);
        }
    }

    @Override
    public void showProgress() {
        if (null != listener) {
            listener.showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (null != listener) {
            listener.hideProgress();
        }
    }

    public void loadData() {
        presenter.loadData(Utils.isConnected(context));
    }

    public interface OnListFragmentInteractionListener extends BaseView {

        void showData(Number number);

    }
}
