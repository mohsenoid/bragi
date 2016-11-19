package com.mirhoseini.bragi.domain.model;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class DataModel {
    Sections section;
    Items item;
    boolean isChecked;

    public DataModel(int number) {
        section = Sections.fromValue(number & 0x3);
        item = Items.fromValue((number >> 2) & 0x1F);
        isChecked = ((number >> 7) & 0x1) == 1;
    }

    public Sections getSection() {
        return section;
    }

    public Items getItem() {
        return item;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
