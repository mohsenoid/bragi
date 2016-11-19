package com.mirhoseini.bragi.numbers.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mirhoseini.bragi.domain.model.DataModel;
import com.mirhoseini.bragi.domain.model.Sections;
import com.mirhoseini.bragi.numbers.adapter.model.Number;
import com.mirhoseini.bragi.numbers.adapter.model.NumberHeader;
import com.mirhoseini.bragi.numbers.adapter.model.NumberItem;
import com.mirhoseini.bragi.numbers.adapter.model.NumberRecord;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class NumbersRecyclerViewAdapter extends RecyclerView.Adapter<NumbersViewHolder> {

    private ArrayList<NumberRecord> records = new ArrayList<>();

    private PublishSubject<Number> notify = PublishSubject.create();

    @Inject
    public NumbersRecyclerViewAdapter() {
    }

    public void setData(ArrayList<DataModel> dataList) {
        this.records = mapDataListToNumberRecord(new ArrayList<>(dataList));
    }

    /**
     * Map ArrayList of {@link DataModel} to ArrayList of {@link NumberRecord}
     *
     * @param dataList ArrayList of {@link DataModel}
     * @return ArrayList of {@link NumberRecord}
     */
    private ArrayList<NumberRecord> mapDataListToNumberRecord(ArrayList<DataModel> dataList) {
        ArrayList<NumberRecord> result = new ArrayList<>();

        Sections section = null;

        for (DataModel data : dataList) {
            if (null != section && section == data.getSection())
                //new item
                result.add(new NumberRecord(NumberRecord.RecordTypes.ITEM, new NumberItem(data.getItem(), data.isChecked())));
            else {
                // new section
                section = data.getSection();
                result.add(new NumberRecord(NumberRecord.RecordTypes.HEADER, new NumberHeader(section)));
                result.add(new NumberRecord(NumberRecord.RecordTypes.ITEM, new NumberItem(data.getItem(), data.isChecked())));
            }
        }

        return result;
    }

    @Override
    public int getItemViewType(int position) {
        return records.get(position).getRecordType();
    }

    @Override
    public NumbersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NumberRecord.RecordTypes recordTypes = NumberRecord.RecordTypes.fromValue(viewType);
        return NumbersViewHolder.makeViewHolder(parent, recordTypes);
    }

    @Override
    public void onBindViewHolder(final NumbersViewHolder holder, int position) {
        final Number number = records.get(position).getNumber();

        holder.setData(number);

        // handle click on records
        Observable.create((Subscriber<? super Number> subscriber) -> holder.getView().setOnClickListener(view -> subscriber.onNext(holder.getData())))
                .subscribe(notify::onNext);
    }

    public Observable<Number> asObservable() {
        return notify.asObservable();
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

}
