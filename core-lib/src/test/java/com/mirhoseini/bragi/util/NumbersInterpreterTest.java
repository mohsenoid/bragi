package com.mirhoseini.bragi.util;

import com.mirhoseini.bragi.domain.model.DataModel;
import com.mirhoseini.bragi.domain.model.Items;
import com.mirhoseini.bragi.domain.model.Sections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class NumbersInterpreterTest {

    private static final int TEST_NUMBER = 140;
    private static final Sections TEST_SECTION = Sections.Section1;
    private static final Items TEST_ITEM = Items.Item4;
    private static final boolean TEST_IS_CHECKED = true;

    @Before
    public void setup() {

    }

    @Test
    public void testInterpreter() throws Exception {
        DataModel data = NumbersInterpreter.interpret(TEST_NUMBER);

        assertEquals(data.getSection(), TEST_SECTION);
        assertEquals(data.getItem(), TEST_ITEM);
        assertEquals(data.isChecked(), TEST_IS_CHECKED);
    }
}
