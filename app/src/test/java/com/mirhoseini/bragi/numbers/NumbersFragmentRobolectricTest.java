package com.mirhoseini.bragi.numbers;

import com.mirhoseini.bragi.BuildConfig;
import com.mirhoseini.bragi.MainActivity;
import com.mirhoseini.bragi.test.support.ShadowSnackbar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Mohsen on 18/11/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class NumbersFragmentRobolectricTest {

    private MainActivity activity;
    private NumbersFragment fragment;

    @Before
    public void setUp() throws Exception {
        // setup activity
        activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(activity);

        // setup fragment
        fragment = (NumbersFragment) activity.getSupportFragmentManager().findFragmentByTag(MainActivity.TAG_NUMBERS_FRAGMENT);
        assertNotNull(fragment);
    }

    @Test
    public void testOnDetach() throws Exception {
        fragment.onDetach();

        assertNull(fragment.listener);
        assertNull(fragment.presenter);
    }

}