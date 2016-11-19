package com.mirhoseini.bragi.numbers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 18/11/2016.
 */

/**
 * Module which extends {@link NumbersModule} and provide Android objects required by {@link NumbersFragment}
 */
@Module
public class AppNumbersModule extends NumbersModule {
    private final Context context;
    private final NumbersFragment.OnListFragmentInteractionListener listener;

    AppNumbersModule(Context context, NumbersFragment fragment) {
        super(fragment);

        this.context = context;

        if (context instanceof NumbersFragment.OnListFragmentInteractionListener) {
            listener = (NumbersFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Provides
    @Numbers
    NumbersFragment.OnListFragmentInteractionListener provideOnListFragmentInteractionListener() {
        return listener;
    }

    @Provides
    @Numbers
    public LinearLayoutManager provideLayoutManager() {
        return new LinearLayoutManager(context);
    }
}
