package com.mirhoseini.bragi.numbers;

import com.mirhoseini.bragi.base.BasePresenter;

/**
 * Created by Mohsen on 18/11/2016.
 */

interface NumbersPresenter extends BasePresenter<NumbersView> {

    void loadData(boolean isConnected);

}
