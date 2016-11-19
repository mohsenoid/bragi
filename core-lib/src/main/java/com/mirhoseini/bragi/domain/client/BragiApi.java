package com.mirhoseini.bragi.domain.client;

import net.bragi.foo.model.NumbersResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Mohsen on 18/11/2016.
 */

public interface BragiApi {

    // http://foo.bragi.net/numbers.json
    @GET("numbers.json")
    Observable<NumbersResponse> getNumbers();

}
