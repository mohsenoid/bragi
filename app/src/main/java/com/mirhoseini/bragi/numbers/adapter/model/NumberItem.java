package com.mirhoseini.bragi.numbers.adapter.model;

import com.mirhoseini.bragi.domain.model.Items;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class NumberItem extends Number {
    Items item;
    boolean isChecked;

    public NumberItem(Items item, boolean isChecked) {
        this.item = item;
        this.isChecked = isChecked;
    }

    public Items getItem() {
        return item;
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
