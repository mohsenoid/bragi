package com.mirhoseini.bragi.numbers.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mirhoseini.bragi.BR;
import com.mirhoseini.bragi.R;
import com.mirhoseini.bragi.numbers.adapter.model.Number;
import com.mirhoseini.bragi.numbers.adapter.model.NumberRecord;

/**
 * Created by Mohsen on 18/11/2016.
 */

class NumbersViewHolder extends RecyclerView.ViewHolder {

    private final View view;
    private final ViewDataBinding binding;
    private final NumberRecord.RecordTypes recordType;
    private Number number;

    private NumbersViewHolder(View view, NumberRecord.RecordTypes recordType) {
        super(view);
        this.view = view;
        this.recordType = recordType;

        binding = DataBindingUtil.bind(view);
    }

    /**
     * Make {@link android.support.v7.widget.RecyclerView.ViewHolder} according to {@link com.mirhoseini.bragi.numbers.adapter.model.NumberRecord.RecordTypes}
     *
     * @param parent     parent {@link ViewGroup}
     * @param recordType record type {@link com.mirhoseini.bragi.numbers.adapter.model.NumberRecord.RecordTypes}
     * @return Numbers ViewHolder
     */
    static NumbersViewHolder makeViewHolder(ViewGroup parent, NumberRecord.RecordTypes recordType) {
        View view = getLayoutView(parent, recordType);
        return new NumbersViewHolder(view, recordType);
    }

    /**
     * Inflate {@link View} according to {@link com.mirhoseini.bragi.numbers.adapter.model.NumberRecord.RecordTypes}
     *
     * @param parent     parent {@link ViewGroup}
     * @param recordType record type {@link com.mirhoseini.bragi.numbers.adapter.model.NumberRecord.RecordTypes}
     * @return Layout View
     */
    private static View getLayoutView(ViewGroup parent, NumberRecord.RecordTypes recordType) {
        int layoutId = getLayoutId(recordType);
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    /**
     * return layout id according to {@link com.mirhoseini.bragi.numbers.adapter.model.NumberRecord.RecordTypes}
     *
     * @param recordType record type {@link com.mirhoseini.bragi.numbers.adapter.model.NumberRecord.RecordTypes}
     * @return layout id
     */
    private static int getLayoutId(NumberRecord.RecordTypes recordType) {
        switch (recordType) {
            case HEADER:
                return R.layout.header_number;
            case ITEM:
                return R.layout.item_number;
        }

        return -1;
    }

    public Number getData() {
        return number;
    }

    public void setData(Number number) {
        this.number = number;

        switch (recordType) {
            case HEADER:
                binding.setVariable(BR.header, number);
                break;
            case ITEM:
                binding.setVariable(BR.item, number);
                break;
        }

        binding.executePendingBindings();
    }

    public View getView() {
        return view;
    }
}
