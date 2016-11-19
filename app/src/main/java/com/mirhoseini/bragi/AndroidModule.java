package com.mirhoseini.bragi;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 18/11/2016.
 */

/**
 * Module which provide Android objects required during whole application lifecycle
 */
@Module
public class AndroidModule {
    private BragiApplication application;

    public AndroidModule(BragiApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }

}
