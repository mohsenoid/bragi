package com.mirhoseini.bragi.numbers;

import com.mirhoseini.bragi.base.BaseView;
import com.mirhoseini.bragi.domain.model.DataModel;

import java.util.ArrayList;

/**
 * Created by Mohsen on 18/11/2016.
 */

interface NumbersView extends BaseView {

    void setDataList(ArrayList<DataModel> dataModel);

}
