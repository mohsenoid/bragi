package com.mirhoseini.bragi.numbers;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 18/11/2016.
 */

@Numbers
@Subcomponent(modules = {
        AppNumbersModule.class
})
public interface NumbersSubComponent {

    void inject(NumbersFragment fragment);

}
