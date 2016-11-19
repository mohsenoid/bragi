package com.mirhoseini.bragi.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.mirhoseini.bragi.ApplicationComponent;
import com.mirhoseini.bragi.BragiApplication;

/**
 * Created by Mohsen on 18/11/2016.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        injectDependencies(BragiApplication.getComponent(), context);

        // can be used for general purpose in all Fragments of Application
    }

    /**
     * An abstract method which inject dependencies to Fragment
     *
     * @param component Application Component
     * @param context   Fragment context
     */
    protected abstract void injectDependencies(ApplicationComponent component, Context context);

}