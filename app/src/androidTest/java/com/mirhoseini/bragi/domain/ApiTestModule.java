package com.mirhoseini.bragi.domain;

import com.mirhoseini.bragi.domain.client.BragiApi;

import retrofit2.Retrofit;

import static org.mockito.Mockito.mock;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class ApiTestModule extends ApiModule {

    @Override
    public BragiApi provideBragiApi(Retrofit retrofit) {
        // replace real MarvelApi with Mock one
        return mock(BragiApi.class);
    }

}
