package com.mirhoseini.bragi;


import com.mirhoseini.bragi.domain.ApiTestModule;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class BragiTestApplication extends BragiApplicationImpl {

    @Override
    public ApplicationTestComponent createComponent() {
        return DaggerApplicationTestComponent
                .builder()
                .androidModule(new AndroidModule(this))
                // replace Api Module with Mock one
                .apiModule(new ApiTestModule())
                .build();
    }

}
