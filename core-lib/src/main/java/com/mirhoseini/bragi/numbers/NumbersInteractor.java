package com.mirhoseini.bragi.numbers;

import com.mirhoseini.bragi.base.BaseInteractor;
import com.mirhoseini.bragi.domain.NumbersResponse;

import rx.Observable;

/**
 * Created by Mohsen on 18/11/2016.
 */

interface NumbersInteractor extends BaseInteractor {

    Observable<NumbersResponse> getNumbers();

}
