package com.mirhoseini.bragi.util;

import com.mirhoseini.bragi.domain.model.DataModel;
import com.mirhoseini.bragi.domain.model.Items;
import com.mirhoseini.bragi.domain.model.Sections;

/**
 * Created by Mohsen on 18/11/2016.
 */


public class NumbersInterpreter {

    /**
     * Numbers Interpreter which know how to convert integer numbers to {@link DataModel}
     *
     * @param number input number
     * @return a new {@link DataModel} filled with information interpreted from number
     */
    public static DataModel interpret(int number) {
        Sections section = Sections.fromValue(number & 0x3);
        Items item = Items.fromValue((number >> 2) & 0x1F);
        boolean isChecked = ((number >> 7) & 0x1) == 1;

        return new DataModel(section, item, isChecked);
    }

}
