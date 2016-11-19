package com.mirhoseini.bragi.util;

import android.content.Context;

import com.mirhoseini.utils.Utils;

import javax.inject.Inject;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class StateManagerImpl implements StateManager {

    private Context context;

    @Inject
    public StateManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean isConnect() {
        return Utils.isConnected(context);
    }
}
