package com.mirhoseini.bragi.numbers;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 18/11/2016.
 */

/**
 * Application SubComponent which inject {@link com.mirhoseini.bragi.ApplicationModule} and {@link AppNumbersModule} members into {@link NumbersFragment}
 */
@Numbers
@Subcomponent(modules = {
        AppNumbersModule.class
})
public interface NumbersSubComponent {

    void inject(NumbersFragment fragment);

}
