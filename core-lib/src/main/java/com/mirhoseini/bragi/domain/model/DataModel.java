package com.mirhoseini.bragi.domain.model;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class DataModel {

    private final Sections section;
    private final Items item;
    private final boolean isChecked;

    public DataModel(Sections section, Items item, boolean isChecked) {
        this.section = section;
        this.item = item;
        this.isChecked = isChecked;
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
