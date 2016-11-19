package com.mirhoseini.bragi;

import com.mirhoseini.bragi.domain.ApiModule;
import com.mirhoseini.bragi.domain.ClientModule;
import com.mirhoseini.bragi.numbers.AppNumbersModule;
import com.mirhoseini.bragi.numbers.NumbersSubComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mohsen on 18/11/2016.
 */

@Singleton
@Component(modules = {
        AndroidModule.class,
        ApplicationModule.class,
        ApiModule.class,
        ClientModule.class
})
public interface ApplicationComponent {

    void inject(MainActivity activity);

    NumbersSubComponent plus(AppNumbersModule module);
}