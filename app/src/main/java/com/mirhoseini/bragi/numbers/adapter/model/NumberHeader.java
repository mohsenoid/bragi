package com.mirhoseini.bragi.numbers.adapter.model;

import com.mirhoseini.bragi.domain.model.Sections;

/**
 * Created by Mohsen on 18/11/2016.
 */

/**
 * Model which holds Headers row values
 */
public class NumberHeader extends Number {
    Sections section;

    public NumberHeader(Sections section) {
        this.section = section;
    }

    public Sections getSection() {
        return section;
    }

    @Override
    public String toString() {
        return section.toString();
    }
}
